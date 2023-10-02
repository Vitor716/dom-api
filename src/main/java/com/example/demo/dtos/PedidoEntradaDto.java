package com.example.demo.dtos;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.model.ItemPedido;
import com.example.demo.model.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class PedidoEntradaDto {
	@NotBlank(message = "obrigatório")
	private Usuario usuario;
	
	@NotBlank(message = "obrigatório")
	private List<ItemPedido> item;
	
	@NotBlank(message = "obrigatório")
	@JsonFormat(pattern = "ddMMYYYY")
	private LocalDate date;
}
