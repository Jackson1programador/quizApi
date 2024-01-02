package com.quiz.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException extends NegocioException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(String mensagem) {
		super(mensagem);
	}

	public CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}

}
