package com.example.demo.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriaSaidaDto;
import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.dtos.PedidoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Categoria;
import com.example.demo.model.ItemPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Produto;
import com.example.demo.model.StatusPedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.validator.PedidoValidator;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private PedidoValidator pedidoValidator;

	@Transactional
	public PedidoSaidaDto criar(PedidoEntradaDto pedidoEntradaDto) {
		try {

			Pedido pedido = mapper.map(pedidoEntradaDto, Pedido.class);

			if (pedidoEntradaDto.getIdUsuario() != null) {
				Optional<Usuario> optionalUsuario = usuarioRepository.findById(pedidoEntradaDto.getIdUsuario());

				if (!optionalUsuario.isPresent()) {
					log.error("Criar, usuario não encontrado: id={}", pedidoEntradaDto.getIdUsuario());
					throw new ErroDeNegocioException(TabelaDeErros.USUARIO_NAO_ENCONTRADO);
				}

				Usuario usuario = optionalUsuario.get();
				pedido.setUsuario(usuario);
			}

			List<ItemPedido> itensPedido = itemPedidoRepository.findAllById(pedidoEntradaDto.getItens());

			for (ItemPedido itemPedido : itensPedido) {
				if (!itemPedido.getUsuario().getId().equals(pedidoEntradaDto.getIdUsuario())) {
					throw new ErroDeNegocioException(TabelaDeErros.USUARIO_DIFERENTE_NOS_ITENS);

				}
			}

			if (itensPedido.size() != pedidoEntradaDto.getItens().size()) {
				throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
			}

			pedido.setItens(itensPedido);
			pedido.setData(LocalDate.now());
			pedido.setStatus(StatusPedido.EM_ANDAMENTO);

			Pedido registroPedidoBanco = pedidoRepository.save(pedido);

			PedidoSaidaDto pedidoSaidaDto = mapper.map(registroPedidoBanco, PedidoSaidaDto.class);

			return pedidoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error("criar, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	public List<PedidoSaidaDto> pegarTodos() {
		try {
			List<Pedido> pedidos = pedidoRepository.findAll();

			List<PedidoSaidaDto> pedidoSaidaDtoList = mapper.map(pedidos, new TypeToken<List<PedidoSaidaDto>>() {
			}.getType());

			for (Pedido pedido : pedidos) {
				double total = calcular(pedido);
				PedidoSaidaDto pedidoSaidaDto = mapper.map(pedido, PedidoSaidaDto.class);
				pedidoSaidaDto.setTotal(total);
				pedidoSaidaDtoList.add(pedidoSaidaDto);
			}

			return pedidoSaidaDtoList;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	public List<PedidoSaidaDto> pegarTodosFechados() {
		try {
			List<Pedido> pedidos = pedidoRepository.findPedidosFechados();

			List<PedidoSaidaDto> pedidoSaidaDtoList = mapper.map(pedidos, new TypeToken<List<PedidoSaidaDto>>() {
			}.getType());

			for (Pedido pedido : pedidos) {
				double total = calcular(pedido);
				PedidoSaidaDto pedidoSaidaDto = mapper.map(pedido, PedidoSaidaDto.class);
				pedidoSaidaDto.setTotal(total);
				pedidoSaidaDtoList.add(pedidoSaidaDto);
			}

			return pedidoSaidaDtoList;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	public List<PedidoSaidaDto> pegarTodosAbertos() {
		try {
			List<Pedido> pedidos = pedidoRepository.findPedidosAbertos();

			List<PedidoSaidaDto> pedidoSaidaDtoList = mapper.map(pedidos, new TypeToken<List<PedidoSaidaDto>>() {
			}.getType());

			for (Pedido pedido : pedidos) {
				double total = calcular(pedido);
				PedidoSaidaDto pedidoSaidaDto = mapper.map(pedido, PedidoSaidaDto.class);
				pedidoSaidaDto.setTotal(total);
				pedidoSaidaDtoList.add(pedidoSaidaDto);
			}

			return pedidoSaidaDtoList;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	@Transactional
	public void excluir(Integer id) {

		pedidoValidator.excluir(id);

		Optional<Pedido> optional = pedidoRepository.findById(id);

		if (optional.isPresent()) {
			Pedido pedido = optional.get();

			if (pedido.getStatus() != StatusPedido.FECHADO) {
				pedido.setStatus(StatusPedido.FECHADO);

				for (ItemPedido itemPedido : pedido.getItens()) {
					Produto produto = itemPedido.getProduto();
					int quantidadeItem = itemPedido.getQuantidade();
					produto.setQuantidade(produto.getQuantidade() + quantidadeItem);
				}
				pedidoRepository.save(pedido);
			}
		} else {
			throw new ErroDeNegocioException(TabelaDeErros.PEDIDO_NAO_ENCONTRADO);
		}
	}

	@Transactional
	public Double calcular(Pedido pedido) {
		double total = 0.0;

		for (ItemPedido item : pedido.getItens()) {
			Produto produto = item.getProduto();
			Integer quantidade = item.getQuantidade();
			double precoUnitario = produto.getPreco();
			double subtotalItem = precoUnitario * quantidade;
			total += subtotalItem;
		}

		return total;
	}
}
