package com.example.demo.controller;

import java.io.IOError;
import java.io.IOException;
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

import com.example.demo.dtos.produto.ProdutoEntradaDto;
import com.example.demo.dtos.produto.ProdutoSaidaDto;
import com.example.demo.service.ProdutoService;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Validated
@RequestMapping("v2/produto")
public class ProdutoController {
	@Autowired
	private ProdutoService produtoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ProdutoSaidaDto criar(@Valid @RequestBody ProdutoEntradaDto produtoEntradaDto) {
		log.info("salvar : {}", produtoEntradaDto);
		return produtoService.criar(produtoEntradaDto);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("id/{id}")
	public void editar(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id,
			@Valid @RequestBody ProdutoEntradaDto produtoEntradaDto) {
		produtoService.editar(id, produtoEntradaDto);
	}

	@GetMapping("id/{id}")
	public ProdutoSaidaDto pegarUm(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id) {
		return produtoService.pegarUm(id);
	}

	@GetMapping
	public List<ProdutoSaidaDto> listar() {
		return produtoService.listar();
	}

	@GetMapping("grafico")
	public void gerarGrafico(HttpServletResponse reponse) throws IOException{
		produtoService.gerarGrafico(reponse);
	}
	
	@DeleteMapping
	public void excluir(Integer id) {
		log.info("excluir : {}", id);

		produtoService.excluir(id);
	}
	
}
