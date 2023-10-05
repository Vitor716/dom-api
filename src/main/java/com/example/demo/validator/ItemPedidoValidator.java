package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.repository.ItemPedidoRepository;

@Component
public class ItemPedidoValidator {
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
}
