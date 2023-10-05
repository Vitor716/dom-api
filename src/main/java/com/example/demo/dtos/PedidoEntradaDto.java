package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.ItemPedido;
import com.example.demo.model.StatusPedido;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoEntradaDto {
	
	private Integer idUsuario;
	
	private StatusPedido status;
	
	@NotNull(message = "Itens do pedido são obrigatórios")
	@Size(min = 1, message = "Pelo menos um item deve ser fornecido")
	private List<Integer> itens;	
	
	@NotNull(message = "obrigatório")
	@JsonFormat(pattern = "ddMMyyyy")
	private LocalDate data;

}
