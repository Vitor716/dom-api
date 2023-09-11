package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {
	
	CATEGORIA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "Categoria não encontrada"),
	CATEGORIA_JA_UTILIZADA(HttpStatus.PRECONDITION_FAILED, "Categoria já utilizada");
	
	private final HttpStatus httpStatus; 
	private final String erro;
}
