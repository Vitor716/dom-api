package com.example.demo.validator;

import org.springframework.stereotype.Component;

import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;

@Component
public class PedidoValidator {
	public void criar(PedidoEntradaDto pedidoEntradaDto) {
		if(pedidoEntradaDto.getItens() == null || pedidoEntradaDto.getItens().isEmpty()) {
			throw new ErroDeNegocioException(TabelaDeErros.LISTA_DE_PRODUTOS_VAZIA);
		}
	}
	
}
