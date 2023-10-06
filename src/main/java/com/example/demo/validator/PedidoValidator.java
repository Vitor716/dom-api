package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.repository.PedidoRepository;

@Component
public class PedidoValidator {
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public void criar(PedidoEntradaDto pedidoEntradaDto) {
		if(pedidoEntradaDto.getItens() == null || pedidoEntradaDto.getItens().isEmpty()) {
			throw new ErroDeNegocioException(TabelaDeErros.LISTA_DE_PRODUTOS_VAZIA);
		}
	}
	
	public void excluir(Integer id) {
		if (!pedidoRepository.existsById(id)) {
			throw new ErroDeNegocioException(TabelaDeErros.PEDIDO_NAO_ENCONTRADO);
		}
	}
}
