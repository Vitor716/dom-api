package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoriaEntradaDto;
import com.example.demo.dtos.CategoriaSaidaDto;
import com.example.demo.service.CategoriaService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Validated
@RequestMapping("v1/categoria")
public class CategoriaController {
	@Autowired
	private CategoriaService categoryService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public CategoriaSaidaDto criar(@Valid @RequestBody CategoriaEntradaDto categoryEntradaDto) {
		log.info("saldar : {}", categoryEntradaDto);
		
		return categoryService.criar(categoryEntradaDto);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("id/{id}")
	public void editar(
			@Positive(message = "O ID não pode ser negativo ou zero") 
			@PathVariable Integer id,
			@Valid @RequestBody CategoriaEntradaDto categoryEntradaDto) {
		
			categoryService.editar(id, categoryEntradaDto);
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("id/{id}")
	public void excluir(
			@Positive(message = "O ID não pode ser negativo ou zero") 
			@PathVariable Integer id) {
		
			categoryService.excluir(id);
	}
	
	@GetMapping("id/{id}")
	public CategoriaSaidaDto pegarUm(
			@Positive(message = "O ID não pode ser negativo ou zero") 
			@PathVariable Integer id) {
		
			return categoryService.pegarUm(id);
	}
	
	@GetMapping
	public List<CategoriaSaidaDto> pegarTodos() {
			return categoryService.pegarTodos();
	}
}
