package com.example.demo.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.dtos.produto.ProdutoSaidaDto;
import com.example.demo.model.Produto;
import com.example.demo.repository.ProdutoRepository;

@Service
public class ProdutoService {
	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ModelMapper mapper;
	
	public ProdutoSaidaDto criar(ProdutoEntradaDto produtoEntradaDto) {
		Produto produto = mapper.map(produtoEntradaDto, Produto.class);
		
		Produto registroProdutoBanco = produtoRepository.save(produto);
		
		ProdutoSaidaDto produtoSaidaDto = mapper.map(registroProdutoBanco, ProdutoSaidaDto.class);
		
		return produtoSaidaDto;
	}
}
