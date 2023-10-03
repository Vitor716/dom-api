package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.UsuarioEntradaDto;
import com.example.demo.dtos.UsuarioSaidaDto;
import com.example.demo.service.UsuarioService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("v2/usuario")
public class UsuarioController {
	@Autowired
	private UsuarioService usuarioService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public UsuarioSaidaDto criar(@Valid @RequestBody UsuarioEntradaDto usuarioEntradaDto) {
		log.info("salvar : {}", usuarioEntradaDto);
		return usuarioService.criar(usuarioEntradaDto);
	}

	@GetMapping
	public List<UsuarioSaidaDto> listar() {
		return usuarioService.listar();
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("id/{id}")
	public void excluir(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id) {
		log.info("excluir : {}", id);

		usuarioService.excluir(id);
	}
}
