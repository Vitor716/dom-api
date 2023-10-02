package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.PedidoEntradaDto;
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
	
	@PostMapping
	public String comprar(@Valid @RequestBody PedidoEntradaDto pedidoEntradaDto) {
		return pedidoService.comprar(PedidoEntradaDto);
	}
}
