package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoriaEntradaDto {

	@NotBlank(message = "obrigatório")
	@Size(min = 0, max = 200, message = "máximo 200 caracteres")
	private String nome;

	@Size(min = 0, max = 500, message = "máximo 500 caracteres")
	private String descricao;
}
