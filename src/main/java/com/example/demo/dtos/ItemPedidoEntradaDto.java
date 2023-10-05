package com.example.demo.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ItemPedidoEntradaDto {
	@NotNull(message = "obrigatório")
	private Integer idProduto;
	
	@NotNull(message = "obrigatório")
	private Integer idUsuario;
	
	@NotNull(message = "obrigatório")
	@Min(value = 1, message = "O deve ser maior que zero.")
	private Integer quantidade;
}
