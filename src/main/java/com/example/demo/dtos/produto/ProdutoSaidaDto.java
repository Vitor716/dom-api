package com.example.demo.dtos.produto;

import com.example.demo.dtos.categoria.CategoriaSaidaDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProdutoSaidaDto {
	private Integer id;
	private String nome;
	private String descricao;
	private String marca;
	private double preco;
	private Integer quantidade;
	private String imagem;
	private Integer min_quantidade;
	private CategoriaSaidaDto categoria;
}
