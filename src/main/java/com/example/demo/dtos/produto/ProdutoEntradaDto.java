package com.example.demo.dtos.produto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoEntradaDto {
	@NotBlank(message = "obrigatório")
	@Size(min = 0, max = 200, message = "máximo 200 caracteres")
	private String nome;

	@Size(min = 0, max = 500, message = "máximo 500 caracteres")
	private String descricao;

	@NotBlank(message = "obrigatório")
	@Size(min = 0, max = 200, message = "máximo 200 caracteres")
	private String marca;

	@DecimalMin(value = "0.01", message = "valor maior igual que 0.01")
	@DecimalMax(value = "1000000.00", message = "valor menor ou igual a 1000000.00")
	@Digits(integer = 7, fraction = 2, message = "inválido")
	@NotNull(message = "obrigatório")
	private double preco;

	@Min(value = 1, message = "O deve ser maior que zero.")
	@NotNull(message = "obrigatório")
	private Integer quantidade;
	private String imagem;

	@Min(value = 1, message = "O deve ser maior que zero.")
	@NotNull(message = "obrigatório")
	private Integer min_quantidade;

	@NotNull(message = "obrigatório")
	private Integer idCategoria;
}
