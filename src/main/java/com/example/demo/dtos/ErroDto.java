package com.example.demo.dtos;

import java.util.List;

import com.example.demo.model.Erro;

import lombok.Data;

@Data
public class ErroDto {
	private String erro;
	private List<Erro> validacoes;
	
}
