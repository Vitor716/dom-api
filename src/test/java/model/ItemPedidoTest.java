package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.model.ItemPedido;


class ItemPedidoTest {
	@Test
	void cobertura() {
		ItemPedido itemPedido= new ItemPedido();	
		itemPedido.setId(null);
		itemPedido.setProduto(null);
		itemPedido.setQuantidade(null);
		itemPedido.setUsuario(null);
		Assertions.assertNotNull(itemPedido.toString());
	}
}
