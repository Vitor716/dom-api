package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ErroDeNegocioException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private final HttpStatus httpStatus;
	private final String erro;

	public ErroDeNegocioException(TabelaDeErros tabela) {
		this.httpStatus = tabela.getHttpStatus();
		this.erro = tabela.getErro();
	}
}
