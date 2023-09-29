package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.jfree.util.Log;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.CategoriaEntradaDto;
import com.example.demo.dtos.CategoriaSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.validator.CategoriaValidator;

import jakarta.transaction.Transactional;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoriaValidator categoriaValidator;

	public CategoriaSaidaDto criar(CategoriaEntradaDto categoriaEntradaDto) {
		try {
			categoriaValidator.criar(categoriaEntradaDto);

			Categoria categoria = mapper.map(categoriaEntradaDto, Categoria.class);

			Categoria registroCategoriaBanco = categoriaRepository.save(categoria);

			CategoriaSaidaDto categoriaSaidaDto = mapper.map(registroCategoriaBanco, CategoriaSaidaDto.class);

			return categoriaSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error("criar, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	@Transactional
	public void editar(Integer id, CategoriaEntradaDto categoriaEntradaDto) {
		try {
			Optional<Categoria> optional = categoriaRepository.findById(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
			}

			Categoria registroCategoriaBanco = optional.get();

			categoriaValidator.editar(id, categoriaEntradaDto);

			mapper.map(categoriaEntradaDto, registroCategoriaBanco);

			categoriaRepository.save(registroCategoriaBanco);
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error("editar, erro generico e=", e);
			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	@Transactional
	public void excluir(Integer id) {
		try {
			categoriaValidator.excluir(id);

			categoriaRepository.deleteById(id);
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public CategoriaSaidaDto pegarUm(Integer id) {
		try {
			Optional<Categoria> optional = categoriaRepository.findById(id);

			if (!optional.isPresent()) {
				throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
			}

			Categoria registroCategoriaBanco = optional.get();

			CategoriaSaidaDto categoriaSaidaDto = mapper.map(registroCategoriaBanco, CategoriaSaidaDto.class);

			return categoriaSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	public List<CategoriaSaidaDto> pegarTodos() {
		try {
			List<Categoria> categorias = categoriaRepository.findAll();

			List<CategoriaSaidaDto> categoriaSaidaDto = mapper.map(categorias,
					new TypeToken<List<CategoriaSaidaDto>>() {
					}.getType());

			return categoriaSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			Log.error(e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}
}
