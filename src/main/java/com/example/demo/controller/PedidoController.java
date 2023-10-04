package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.PedidoEntradaDto;
import com.example.demo.dtos.PedidoSaidaDto;
import com.example.demo.model.Pedido;
import com.example.demo.service.PedidoService;

import jakarta.validation.Valid;
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
	public Pedido criar(@Valid @RequestBody Pedido pedido) {
//		log.info("criar : {}", pedidoEntradaDto);

		return pedidoService.criar(pedido);
	}
}
