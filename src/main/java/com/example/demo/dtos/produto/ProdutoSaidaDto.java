package com.example.demo.dtos.produto;
import com.example.demo.dtos.categoria.CategoriaSaidaDto;
import com.example.demo.model.Categoria;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
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
