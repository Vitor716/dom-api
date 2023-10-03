package com.example.demo.service;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.UsuarioEntradaDto;
import com.example.demo.dtos.UsuarioSaidaDto;
import com.example.demo.exception.ErroDeNegocioException;
import com.example.demo.exception.TabelaDeErros;
import com.example.demo.model.Usuario;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.validator.UsuarioValidator;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Autowired
	private ModelMapper mapper;

	@Autowired
	private UsuarioValidator usuarioValidator;

	@Transactional
	public UsuarioSaidaDto criar(UsuarioEntradaDto usuarioEntradaDto) {
		try {
			usuarioValidator.criar(usuarioEntradaDto);

			Usuario usuario = mapper.map(usuarioEntradaDto, Usuario.class);

			log.info("criar, mapeamento: usuarioEntradaDto={}, entity={}", usuarioEntradaDto, usuario);

			Usuario registroUsuarioBanco = usuarioRepository.save(usuario);

			UsuarioSaidaDto usuarioSaidaDto = mapper.map(registroUsuarioBanco, UsuarioSaidaDto.class);

			return usuarioSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}
	}

	public List<UsuarioSaidaDto> listar() {
		try {
			List<Usuario> usuarios = usuarioRepository.findAll();

			List<UsuarioSaidaDto> usuarioSaidaDto = mapper.map(usuarios, new TypeToken<List<UsuarioSaidaDto>>() {
			}.getType());

			log.info("listar, mapeamento: usuarioSaidaDto={}", usuarioSaidaDto);

			return usuarioSaidaDto;
		} catch (ErroDeNegocioException e) {
			throw e;
		} catch (Exception e) {
			log.error("pegarUm, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}

	@Transactional
	public void excluir(Integer id) {
		try {
			usuarioValidator.excluir(id);

			log.info("excluir, mapeamento: id={}", id);

			usuarioRepository.deleteById(id);
		} catch (Exception e) {
			log.error("excluir, erro generico: e=", e);

			throw new ErroDeNegocioException(TabelaDeErros.ERRO_DE_SISTEMA);
		}

	}
}
