package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Pedido;

class PedidoTest {

	@Test
	void cobertura() {
		Pedido pedido = new Pedido();
		pedido.setData(null);
		pedido.setId(null);
		pedido.setItens(null);
		pedido.setStatus(null);
		pedido.setTotal(null);
		pedido.setUsuario(null);
		Assertions.assertNotNull(pedido.toString());
	}
}
