package com.example.demo.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity()
@Table(name = "pedido")
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;

	@Column(nullable = false)
	@JsonFormat(pattern = "ddMMyyyy")
	private LocalDate data;

	@ManyToMany
	@JoinColumn(name = "item_id", nullable = false)
	private List<ItemPedido> itens;// Usando ArrayList como uma implementação de List

	// Talvez adicionar uma flag de pago ou não e quando for pagar passar o id para
	// pagar o produto
	// E se for exluir o pedido também passa o id
	// E tambem da para listar todos os pedidos feitos por todos e por usuario
	// especifico
	// Talvez vai ter que criar uma classe de pagamento
	// Quando pago vai diminuar a quantidade de produto no
	// Não pode comprar mais de 20 produtos
	// Não pode registrar menos de 20 produtos no banco
}
