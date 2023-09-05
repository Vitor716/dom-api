package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.dtos.produto.ProdutoSaidaDto;
import com.example.demo.service.ProdutoService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
@RequestMapping("v1/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;
	
	
	@PostMapping
	public ProdutoSaidaDto criar(@Valid @RequestBody ProdutoEntradaDto produtoEntradaDto) {
		return produtoService.criar(produtoEntradaDto);
	}
}
