package com.example.demo.dtos;

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
//	
//	@NotNull(message = "obrigatório")
//	private Integer quantidade;
}
