package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

	CATEGORIA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "Categoria não encontrada"),
	USUARIO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Usuário não encontrado"),
	CATEGORIA_JA_UTILIZADA(HttpStatus.PRECONDITION_FAILED, "Categoria já utilizada"),
	LISTA_DE_PRODUTOS_VAZIA(HttpStatus.PRECONDITION_FAILED, "Lista de produtos vazia"),
	NOME_JA_EM_USO(HttpStatus.PRECONDITION_FAILED, "Nome já em uso"),
	PRODUTO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Produto não encontrado"),
	ERRO_DE_SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "Sistema indisponível");
	
	
	private final HttpStatus httpStatus;
	private final String erro;
}
