package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.repository.ProdutoRepository;

@Component
public class ProdutoValidator {
	@Autowired
	private ProdutoRepository produtoRepository;
	
	public void excluir(Integer id) {
		if (!produtoRepository.existsById(id)) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
		}
	}
}
