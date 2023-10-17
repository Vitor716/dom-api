package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ItemPedidoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.ItemPedido;
import com.example.demo.model.Produto;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.ProdutoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.validator.ItemPedidoValidator;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class ItemPedidoService {

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private ItemPedidoValidator itemPedidoValidator;

	@Transactional
	public ItemPedidoSaidaDto criar(ItemPedidoEntradaDto itemPedidoEntradaDto) {
		try {

			ItemPedido itemPedido = mapper.map(itemPedidoEntradaDto, ItemPedido.class);

			if (itemPedidoEntradaDto.getIdProduto() != null && itemPedidoEntradaDto.getIdUsuario() != null) {
				boolean usuarioPossuiItemPedido = itemPedidoRepository.existsByUsuarioIdAndProdutoId(
						itemPedidoEntradaDto.getIdUsuario(), itemPedidoEntradaDto.getIdProduto());

				if (usuarioPossuiItemPedido) {
					log.error("Usuário já possui um item de pedido com o mesmo produto: idUsuario={}, idProduto={}",
							itemPedidoEntradaDto.getIdUsuario(), itemPedidoEntradaDto.getIdProduto());
					throw new ErroDeNegocioException(TabelaDeErros.USUARIO_COM_ITEM_DUPLICADO);
				}

				Optional<Produto> optionalProduto = produtoRepository.findById(itemPedidoEntradaDto.getIdProduto());
				Optional<Usuario> optionalUsuario = usuarioRepository.findById(itemPedidoEntradaDto.getIdUsuario());

				if (!optionalProduto.isPresent()) {
					log.error("Criar, produto não encontrado: id={}", itemPedidoEntradaDto.getIdProduto());
					throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
				}

				if (!optionalUsuario.isPresent()) {
					log.error("Criar, usuario não encontrado: id={}", itemPedidoEntradaDto.getIdUsuario());
					throw new ErroDeNegocioException(TabelaDeErros.USUARIO_NAO_ENCONTRADO);
				}

				Usuario usuario = optionalUsuario.get();
				Produto produto = optionalProduto.get();
				itemPedido.setProduto(produto);
				itemPedido.setUsuario(usuario);

				if (itemPedidoEntradaDto.getQuantidade() > produto.getQuantidade()
						|| produto.getQuantidade() < produto.getMin_quantidade()) {
					log.error("Criar, quantidade maior que no produto: id={}", itemPedidoEntradaDto.getQuantidade());
					throw new ErroDeNegocioException(TabelaDeErros.QUANTIDADE_MAIOR);
				}

				produto.setQuantidade(produto.getQuantidade() - itemPedidoEntradaDto.getQuantidade());
			}

			ItemPedido registroItemPedidoBanco = itemPedidoRepository.save(itemPedido);

			ItemPedidoSaidaDto itemPedidoSaidaDto = mapper.map(registroItemPedidoBanco, ItemPedidoSaidaDto.class);

			return itemPedidoSaidaDto;

		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error("criar, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	public void editar(Integer id, ItemPedidoEntradaDto itemPedidoEntradaDto) {
		try {
			Optional<ItemPedido> optionalItemPedido = itemPedidoRepository.findById(id);

			ItemPedido itemPedido = optionalItemPedido.get();

			if (!itemPedido.getUsuario().getId().equals(itemPedidoEntradaDto.getIdUsuario())) {
				throw new ErroDeNegocioException(TabelaDeErros.ID_USUARIO_NAO_PODE_SER_ALTERADO);
			}

			if (!optionalItemPedido.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
			}

			if (itemPedidoEntradaDto.getIdProduto() != null) {
				Optional<Produto> optionalProduto = produtoRepository.findById(itemPedidoEntradaDto.getIdProduto());

				if (!optionalProduto.isPresent()) {
					log.error("editar, produto não encontrado: id={}", itemPedidoEntradaDto.getIdProduto());
					throw new ErroDeNegocioException(TabelaDeErros.PRODUTO_NAO_ENCONTRADO);
				}

				Produto produto = optionalProduto.get();
				itemPedido.setProduto(produto);

				produto.setQuantidade(produto.getQuantidade() - itemPedidoEntradaDto.getQuantidade());

			}

			if (itemPedidoEntradaDto.getQuantidade() != null) {
				itemPedido.setQuantidade(itemPedidoEntradaDto.getQuantidade());
			}

			mapper.map(itemPedidoEntradaDto, itemPedido);

			log.info("editar, mapeamento: produtoEntradaDto={}, entity={}", itemPedidoEntradaDto, itemPedido);
			itemPedidoRepository.save(itemPedido);
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ItemPedidoSaidaDto pegarUm(Integer id) {
		try {
			Optional<ItemPedido> optional = itemPedidoRepository.findById(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
			}

			ItemPedido registroItemPedidoBanco = optional.get();

			ItemPedidoSaidaDto itemPedidoSaidaDto = mapper.map(registroItemPedidoBanco, ItemPedidoSaidaDto.class);

			return itemPedidoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public ItemPedidoSaidaDto listarPorUsuario(Integer usuarioId) {
		try {
			Optional<ItemPedido> optional = itemPedidoRepository.findByUsuarioId(usuarioId);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
			}

			ItemPedido registroItemPedidoBanco = optional.get();

			ItemPedidoSaidaDto itemPedidoSaidaDto = mapper.map(registroItemPedidoBanco, ItemPedidoSaidaDto.class);

			return itemPedidoSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public List<ItemPedidoSaidaDto> pegarTodos() {
		try {
			List<ItemPedido> itensPedidos = itemPedidoRepository.findAll();

			List<ItemPedidoSaidaDto> itemPedidoSaidaDtos = mapper.map(itensPedidos,
					new TypeToken<List<ItemPedidoSaidaDto>>() {
					}.getType());

			return itemPedidoSaidaDtos;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	@Transactional
	public void excluir(Integer id) {
		try {
			itemPedidoValidator.excluir(id);

			Optional<ItemPedido> optionalItemPedido = itemPedidoRepository.findById(id);

			ItemPedido itemPedido = optionalItemPedido.get();
			Integer itemPedidoQuantidade = itemPedido.getQuantidade();
			Produto produto = itemPedido.getProduto();

			produto.setQuantidade(produto.getQuantidade() + itemPedidoQuantidade);
			produtoRepository.save(produto);

			itemPedidoRepository.deleteById(id);
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}
}
