package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;


import org.hibernate.validator.internal.engine.path.PathImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dtos.ErroDto;
import com.example.demo.model.Erro;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

import com.example.demo.exception.ErroDeNegocioException;


@RestControllerAdvice
public class ErrorController {
	
	@Autowired
	private MessageSource messageSource;

	@ExceptionHandler(ErroDeNegocioException.class)
	@ResponseBody
	public ResponseEntity<ErroDto> handle(ErroDeNegocioException e) {
		ErroDto erroDto = new ErroDto();
		erroDto.setErro(e.getErro());

		return ResponseEntity.status(e.getHttpStatus()).body(erroDto);
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(BindException.class)
	@ResponseBody
	public ErroDto handle(BindException exception) {
		List<Erro> validacoes = new ArrayList<>();

		for (FieldError error : exception.getBindingResult().getFieldErrors()) {
			String mensagem = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			Erro erro = new Erro(error.getField(), mensagem);
			validacoes.add(erro);
		}

		ErroDto erroDto = new ErroDto();
		erroDto.setErro(HttpStatus.BAD_REQUEST.name());
		erroDto.setValidacoes(validacoes);

		return erroDto;
	}

	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(ConstraintViolationException.class)
	@ResponseBody
	public ErroDto handle(ConstraintViolationException e) {
		List<Erro> validacoes = new ArrayList<>();

		for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
			String path = ((PathImpl) violation.getPropertyPath()).getLeafNode().getName();
			Erro erro = new Erro(path, violation.getMessage());
			validacoes.add(erro);
		}

		ErroDto erroDto = new ErroDto();
		erroDto.setErro(HttpStatus.BAD_REQUEST.name());
		erroDto.setValidacoes(validacoes);

		return erroDto;
	}
}