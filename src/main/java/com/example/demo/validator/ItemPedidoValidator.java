package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ProdutoEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.repository.ItemPedidoRepository;

@Component
public class ItemPedidoValidator {
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	public void excluir(Integer id) {
		if (!itemPedidoRepository.existsById(id)) {
			throw new ErroDeNegocioException(TabelaDeErros.ITEM_NAO_ENCONTRADO);
		}
	}
}
