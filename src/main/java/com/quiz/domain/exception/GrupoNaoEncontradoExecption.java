package com.quiz.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class GrupoNaoEncontradoExecption extends EntidadeNãoEcontradaException {

	public GrupoNaoEncontradoExecption(String mensagem) {
		super(mensagem);
		// TODO Auto-generated constructor stub
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
