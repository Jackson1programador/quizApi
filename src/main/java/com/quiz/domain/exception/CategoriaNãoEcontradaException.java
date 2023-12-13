package com.quiz.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class CategoriaNãoEcontradaException extends EntidadeNãoEcontradaException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoriaNãoEcontradaException(String mensagem) {
		super(mensagem);
	}

}
