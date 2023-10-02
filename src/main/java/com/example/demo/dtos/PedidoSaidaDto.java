package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.ItemPedido;
import com.example.demo.model.Usuario;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoSaidaDto {
	private Integer id;
	private Usuario usuario;;
	private List<ItemPedido> item;
	private LocalDate date;
}
