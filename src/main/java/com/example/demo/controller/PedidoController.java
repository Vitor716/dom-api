package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.CategoriaSaidaDto;
import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.dtos.PedidoSaidaDto;
import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@Validated
@RequestMapping("v2/pedido")
public class PedidoController {
	@Autowired
	private PedidoService pedidoService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	public PedidoSaidaDto criar(@Valid @RequestBody PedidoEntradaDto pedidoEntradaDto) {
		log.info("criar : {}", pedidoEntradaDto);

		return pedidoService.criar(pedidoEntradaDto);
	}
	
	@GetMapping
	public List<PedidoSaidaDto> pegarTodos() {
		return pedidoService.pegarTodos();
	}
	
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	@DeleteMapping("id/{id}")
	public void excluir(@Positive(message = "O ID não pode ser negativo ou zero") @PathVariable Integer id) {

		pedidoService.excluir(id);
	}
}
