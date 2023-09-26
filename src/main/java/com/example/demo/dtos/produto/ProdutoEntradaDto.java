package com.example.demo.dtos.produto;

import lombok.Data;

@Data
public class ProdutoEntradaDto {
	private String nome;
	private String descricao;
	private String marca;
	private double preco;
	private Integer quantidade;
	private String imagem;
	private Integer min_quantidade;
	private Integer idCategoria;
}
