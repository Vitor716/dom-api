package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.StatusPedido;
import com.example.demo.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoSaidaDto {
	private Integer id;
	private StatusPedido status;
	private Usuario usuario;;
	private List<ItemPedidoSaidaDto> itens;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
	private Double total;
}
