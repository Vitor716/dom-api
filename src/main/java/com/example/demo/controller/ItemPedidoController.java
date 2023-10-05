package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.ItemPedidoEntradaDto;
import com.example.demo.dtos.ItemPedidoSaidaDto;
import com.example.demo.service.ItemPedidoService;

import jakarta.validation.Valid;
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
}
