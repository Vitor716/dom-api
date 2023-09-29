package com.example.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "item_pedido")
public class ItemPedido {
	private Integer id;
	private Produto produto;
	private Pedido pedido;
	private Integer quantidade;
}
