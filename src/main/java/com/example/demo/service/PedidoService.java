package com.example.demo.service;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.dtos.PedidoSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Pedido;
import com.example.demo.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ModelMapper mapper;

	@Transactional
	public PedidoSaidaDto criar(PedidoEntradaDto pedidoEntradaDto) {
		try {
			Pedido pedido = mapper.map(pedidoEntradaDto, Pedido.class);
			
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
