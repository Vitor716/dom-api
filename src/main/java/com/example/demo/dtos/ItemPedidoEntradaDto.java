package com.example.demo.dtos;

import com.example.demo.model.Pedido;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@ToString
public class ItemPedidoEntradaDto {
	@NotBlank(message = "obrigatório")
	private Integer idProduto;

	@NotBlank(message = "obrigatório")
	private Integer idPedido;

	@NotBlank(message = "obrigatório")
	@Min(value = 1, message = "O deve ser maior que zero.")
	private Integer quantidade;

}
