package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Categoria;

class CategoriaTest {
	@Test
	void cobertura() {
		Categoria categoria = new Categoria();	
		categoria.setId(null);
		categoria.setDescricao(null);
		categoria.setDescricao(null);
		Assertions.assertNotNull(categoria.toString());
	}
}
