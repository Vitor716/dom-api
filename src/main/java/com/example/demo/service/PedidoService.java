package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.ItemPedido;
import com.example.demo.model.Pedido;
import com.example.demo.model.Usuario;
import com.example.demo.repository.ItemPedidoRepository;
import com.example.demo.repository.PedidoRepository;

import jakarta.transaction.Transactional;

@Service
public class PedidoService {
	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Transactional
	public Pedido comprar(Usuario usuario, List<ItemPedido> itens) {
		Pedido pedido = new Pedido();
		pedido.setUsuario(usuario);
		pedido.setDate(new Date());
		
	}
}
