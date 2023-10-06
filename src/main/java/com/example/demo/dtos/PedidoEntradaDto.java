package com.example.demo.dtos;

import java.util.List;

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

	@NotNull(message = "Itens do pedido são obrigatórios")
	@Size(min = 1, message = "Pelo menos um item deve ser fornecido")
	private List<Integer> itens;
}
