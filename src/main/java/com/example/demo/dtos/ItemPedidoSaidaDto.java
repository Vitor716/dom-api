package com.example.demo.dtos;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ItemPedidoSaidaDto {
	private Integer id;
	private Integer quantidade;
	private ProdutoSaidaDto produto;
}
