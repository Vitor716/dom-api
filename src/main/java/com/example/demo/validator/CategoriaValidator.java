package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.CategoriaEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.repository.CategoriaRepository;

@Component
public class CategoriaValidator {
	@Autowired
	private CategoriaRepository categoriaRepository;

	public void criar(CategoriaEntradaDto categoriaEntradaDto) {
		if (categoriaRepository.existsByNome(categoriaEntradaDto.getNome())) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_JA_UTILIZADA);
		}
	}

	public void editar(Integer id, CategoriaEntradaDto categoriaEntradaDto) {
		if (categoriaRepository.existsByNomeAndIdNot(categoriaEntradaDto.getNome(), id)) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_JA_UTILIZADA);
		}
	}

	public void excluir(Integer id) {
		if (!categoriaRepository.existsById(id)) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
		}
	}

}
