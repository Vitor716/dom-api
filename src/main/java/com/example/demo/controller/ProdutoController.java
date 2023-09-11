package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.dtos.produto.ProdutoSaidaDto;
import com.example.demo.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Validated
@RequestMapping("v1/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ProdutoSaidaDto criar(@Valid @RequestBody ProdutoEntradaDto produtoEntradaDto) {
		log.info("salvar : {}", produtoEntradaDto);
		return produtoService.criar(produtoEntradaDto);
	}
	
	@GetMapping
	public List<ProdutoSaidaDto> listar(){
		return produtoService.listar();
	}
	
	@DeleteMapping
	public void excluir(Integer id) {
		log.info("excluir : {}", id);
		
		produtoService.excluir(id);
	}
}
