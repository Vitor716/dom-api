package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.categoria.CategoriaEntradaDto;
import com.example.demo.dtos.categoria.CategoriaSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.model.Categoria;
import com.example.demo.repository.CategoriaRepository;

@Service
public class CategoriaService {
	@Autowired
	private CategoriaRepository categoryRepository;
	
	@Autowired
	private ModelMapper mapper;
	
	public CategoriaSaidaDto criar(CategoriaEntradaDto categoryEntradaDto) {
		Categoria category = mapper.map(categoryEntradaDto, Categoria.class);
		
		
		if(categoryRepository.existsByNome(categoryEntradaDto.getNome())){
			throw new ErroDeNegocioException(HttpStatus.BAD_REQUEST, "Categoria já utilizada");
		}
		
		Categoria registroCategoryBanco = categoryRepository.save(category);
		
		CategoriaSaidaDto categorySaidaDto = mapper.map(registroCategoryBanco, CategoriaSaidaDto.class);
		
		return categorySaidaDto; 
	}
	
	public void editar(Integer id, CategoriaEntradaDto categoryEntradaDto) {
		Optional<Categoria> optional = categoryRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new ErroDeNegocioException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
		}
		
		Categoria registroCategoryBanco = optional.get();
		
		if(categoryRepository.existsByNomeAndIdNot(categoryEntradaDto.getNome(), id)){
			throw new ErroDeNegocioException(HttpStatus.BAD_REQUEST, "Categoria já em uso");
		}
		
		mapper.map(categoryEntradaDto, registroCategoryBanco);
		
		categoryRepository.save(registroCategoryBanco);
	}
	
	public void excluir(Integer id) {
		if(!categoryRepository.existsById(id)) {
			throw new ErroDeNegocioException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
		}
		categoryRepository.deleteById(id);
	}
	
	public CategoriaSaidaDto pegarUm(Integer id) {
		Optional<Categoria> optional = categoryRepository.findById(id);
		
		if(!optional.isPresent()) {
			throw new ErroDeNegocioException(HttpStatus.NOT_FOUND, "Categoria não encontrada");
		}
		
		Categoria registroCategoryBanco = optional.get();
		
		CategoriaSaidaDto categorySaidaDto = mapper.map(registroCategoryBanco, CategoriaSaidaDto.class);
		
		return categorySaidaDto;
	}
	
	public List<CategoriaSaidaDto> pegarTodos() {
		List<Categoria> categorias = categoryRepository.findAll();
		
		List<CategoriaSaidaDto> categorySaidaDto = mapper.map(categorias, new TypeToken<List<CategoriaSaidaDto>> () {
		}.getType());
		
		return categorySaidaDto;
	}
}
