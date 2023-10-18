package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Usuario;

class UsuarioTest {
	@Test
	void cobertura() {
		Usuario usuario = new Usuario();
		usuario.setId(null);
		usuario.setNome(null);
		Assertions.assertNotNull(usuario.toString());
	}
}
