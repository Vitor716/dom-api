package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer>{
	@Query("SELECT p FROM Pedido p WHERE p.status = 0")
    List<Pedido> findPedidosFechados();
	
	//Ver o status code 
	@Query("SELECT p FROM Pedido p WHERE p.status = 'ABERTO'")
    List<Pedido> findPedidosAbertos();
}
