package model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import com.example.demo.model.Erro;

public class ErroTest {

	@Test
	void testConstrutorEGetters() {
		String campo = "testCampo";
		String mensagem = "testMensagem";
		Erro erro = new Erro(campo, mensagem);
		Assertions.assertEquals(campo, erro.getCampo());
		Assertions.assertEquals(mensagem, erro.getMensagem());
	}

	@Test
	void testSetter() {
		String campo = "testCampo";
		String mensagem = "testMensagem";
		Erro erro = new Erro(null, null);
		erro.setCampo(campo);
		erro.setMensagem(mensagem);
		Assertions.assertEquals(campo, erro.getCampo());
		Assertions.assertEquals(mensagem, erro.getMensagem());
	}
}
