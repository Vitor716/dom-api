package exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;

class ErroDeNegocioExceptionTest {
	@Test
	void testErroTabelaDeErros() {
		TabelaDeErros tabela = TabelaDeErros.NOME_JA_EM_USO;

		ErroDeNegocioException e = new ErroDeNegocioException(tabela);

		Assertions.assertEquals(tabela.getHttpStatus(), e.getHttpStatus());
	}

	@Test
	void testConstrutorComHttpStatusError() {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String erro = "Erro";

		ErroDeNegocioException e = new ErroDeNegocioException(status, erro);

		Assertions.assertEquals(status, e.getHttpStatus());
		Assertions.assertEquals(erro, e.getErro());
	}
}
