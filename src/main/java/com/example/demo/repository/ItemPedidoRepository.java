package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.ItemPedido;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

	@Query("SELECT CASE WHEN COUNT(ip) > 0 THEN true ELSE false END FROM ItemPedido ip WHERE ip.usuario.id = :usuarioId AND ip.produto.id = :produtoId")
	boolean existsByUsuarioIdAndProdutoId(Integer usuarioId, Integer produtoId);
}
