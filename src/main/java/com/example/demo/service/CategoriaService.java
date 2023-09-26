package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.categoria.CategoriaEntradaDto;
import com.example.demo.dtos.categoria.CategoriaSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.validator.CategoriaValidator;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private CategoriaValidator categoriaValidator;

	public CategoriaSaidaDto criar(CategoriaEntradaDto categoriaEntradaDto) {
		Categoria categoria = mapper.map(categoriaEntradaDto, Categoria.class);

		categoriaValidator.criar(categoriaEntradaDto);

		Categoria registroCategoriaBanco = categoriaRepository.save(categoria);

		CategoriaSaidaDto categoriaSaidaDto = mapper.map(registroCategoriaBanco, CategoriaSaidaDto.class);

		return categoriaSaidaDto;
	}

	public void editar(Integer id, CategoriaEntradaDto categoriaEntradaDto) {
		Optional<Categoria> optional = categoriaRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
		}

		Categoria registroCategoriaBanco = optional.get();

		categoriaValidator.editar(id, categoriaEntradaDto);
		
		mapper.map(categoriaEntradaDto, registroCategoriaBanco);

		categoriaRepository.save(registroCategoriaBanco);
	}

	public void excluir(Integer id) {
		categoriaValidator.excluir(id);
		
		categoriaRepository.deleteById(id);
	}

	public CategoriaSaidaDto pegarUm(Integer id) {
		Optional<Categoria> optional = categoriaRepository.findById(id);

		if (!optional.isPresent()) {
			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
		}

		Categoria registroCategoriaBanco = optional.get();

		CategoriaSaidaDto categoriaSaidaDto = mapper.map(registroCategoriaBanco, CategoriaSaidaDto.class);

		return categoriaSaidaDto;
	}

	public List<CategoriaSaidaDto> pegarTodos() {
		List<Categoria> categorias = categoriaRepository.findAll();

		List<CategoriaSaidaDto> categoriaSaidaDto = mapper.map(categorias, new TypeToken<List<CategoriaSaidaDto>>() {
		}.getType());

		return categoriaSaidaDto;
	}
}
