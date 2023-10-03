package com.example.demo.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.demo.dtos.UsuarioEntradaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.repository.UsuarioRepository;

@Component
public class UsuarioValidator {
	@Autowired
	private UsuarioRepository usuarioRepository;

	public void criar(UsuarioEntradaDto usuarioEntradaDto) {
		if (usuarioRepository.existsByNome(usuarioEntradaDto.getNome())) {
			throw new ErroDeNegocioException(TabelaDeErros.NOME_JA_EM_USO);
		}
	}

	public void excluir(Integer id) {
		if (!usuarioRepository.existsById(id)) {
			throw new ErroDeNegocioException(TabelaDeErros.USUARIO_NAO_ENCONTRADO);
		}
	}

//	public void criar(UsuarioEntradaDto usuarioEntradaDto) {
//		if(!usuarioRepository.existsByNome(usuarioEntradaDto.getNome())) {
//			throw new ErroDeNegocioException(TabelaDeErros.CATEGORIA_NAO_ENCONTRADA);
//		}
//	}
}
