package com.example.demo.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TabelaDeErros {

	CATEGORIA_NAO_ENCONTRADA(HttpStatus.NOT_FOUND, "Categoria não encontrada"),
	USUARIO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Usuário não encontrado"),
	USUARIO_COM_ITEM_DUPLICADO(HttpStatus.PRECONDITION_FAILED, "Usuário com o item duplicado"),
	QUANTIDADE_MAIOR(HttpStatus.PRECONDITION_FAILED, "Quantidade maior que o esperado para esse produto"),
	CATEGORIA_JA_UTILIZADA(HttpStatus.PRECONDITION_FAILED, "Categoria já utilizada"),
	LISTA_DE_PRODUTOS_VAZIA(HttpStatus.PRECONDITION_FAILED, "Lista de produtos vazia"),
	NOME_JA_EM_USO(HttpStatus.PRECONDITION_FAILED, "Nome já em uso"),
	PRODUTO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Produto não encontrado"),
	PEDIDO_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Pedido não encontrado"),
	ITEM_NAO_ENCONTRADO(HttpStatus.NOT_FOUND, "Item não encontrado"),
	ERRO_DE_SISTEMA(HttpStatus.INTERNAL_SERVER_ERROR, "Sistema indisponível"),
	ID_USUARIO_NAO_PODE_SER_ALTERADO(HttpStatus.PRECONDITION_FAILED, "O Id do usuário não pode ser trocado"),
	USUARIO_DIFERENTE_NOS_ITENS(HttpStatus.PRECONDITION_FAILED, "Ids usuário diferente"),
	ERRO_DE_VALIDACAO(HttpStatus.BAD_REQUEST, "Erro de Validação");
	private final HttpStatus httpStatus;
	private final String erro;
}
