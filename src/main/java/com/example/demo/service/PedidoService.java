package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ItemPedidoSaidaDto;
import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.dtos.PedidoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.ItemPedido;
import com.example.demo.model.Pedido;
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
			
			if(pedidoEntradaDto.getIdUsuario() != null) {
				Optional<Usuario> optionalUsuario = usuarioRepository.findById(pedidoEntradaDto.getIdUsuario());
				
				if(!optionalUsuario.isPresent()) {
					log.error("Criar, usuario não encontrado: id={}", pedidoEntradaDto.getIdUsuario());
					throw new ErroDeNegocioException(TabelaDeErros.USUARIO_NAO_ENCONTRADO);
				}
				
				Usuario usuario = optionalUsuario.get();
				pedido.setUsuario(usuario);
			}
			
			List<ItemPedido> itensPedido = itemPedidoRepository.findAllById(pedidoEntradaDto.getItens());
			if (itensPedido.size() != pedidoEntradaDto.getItens().size()) {
	            throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
	        }
		
			pedido.setItens(itensPedido);
			
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
}
