package com.example.demo.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UsuarioEntradaDto {
	@NotBlank(message = "obrigatório")
	@Size(min = 0, max = 200, message = "máximo 200 caracteres")
	private String nome;
}
