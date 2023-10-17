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

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ItemPedidoSaidaDto;
import com.example.demo.service.ItemPedidoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Validated
@RequestMapping("v2/itemPedido")
public class ItemPedidoController {
	@Autowired
	private ItemPedidoService itemPedidoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public ItemPedidoSaidaDto criar(@Valid @RequestBody ItemPedidoEntradaDto itemPedidoEntradaDto) {
		log.info("salvar : {}", itemPedidoEntradaDto);
		return itemPedidoService.criar(itemPedidoEntradaDto);
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@PutMapping("id/{id}")
	public void editar(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id,
			@Valid @RequestBody ItemPedidoEntradaDto itemPedidoEntradaDto) {
		itemPedidoService.editar(id, itemPedidoEntradaDto);
	}

	@GetMapping
	public List<ItemPedidoSaidaDto> pegarTodos() {
		return itemPedidoService.pegarTodos();
	}

	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("id/{id}")
	public void excluir(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id) {

		itemPedidoService.excluir(id);
	}

	@GetMapping("id/{id}")
	public ItemPedidoSaidaDto pegarUm(
			@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id) {
		return itemPedidoService.pegarUm(id);
	}

	@GetMapping("listar/{usuarioId}")
	public ItemPedidoSaidaDto listarPorUsuario(
			@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer usuarioId) {
		return itemPedidoService.listarPorUsuario(usuarioId);
	}
}
