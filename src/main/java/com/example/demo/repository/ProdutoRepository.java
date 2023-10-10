package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
	boolean existsByNome(String nome);
	
	Optional<Produto> findByNome(String nome);

	boolean existsByNomeAndIdNot(String nome, Integer id);

	@Query("SELECT p FROM Produto p WHERE p.categoria.nome = :nomeCategoria")
	Optional<Produto> findProdutosByCategoriaNome(@Param("nomeCategoria") String nome);

	@Query("SELECT p FROM Produto p WHERE p.categoria.id = :categoriaId")
	Optional<Produto> findProdutosByCategoriaId(@Param("categoriaId") Integer id);
}
