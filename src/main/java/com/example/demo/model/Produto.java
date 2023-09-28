package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity()
@Table(name = "produto")
public class Produto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(length = 200, unique = true, nullable = false)
	private String nome;

	@Column(length = 500)
	private String descricao;

	@Column(length = 200, unique = true, nullable = false)
	private String marca;

	@Column(nullable = false)
	private double preco;

	@Column(nullable = false)
	private Integer quantidade;

	private String imagem;

	@Column(name = "min_quantidade", nullable = false)
	private Integer min_quantidade;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Categoria categoria;
}
