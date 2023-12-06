package com.quiz.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class QuizSemRespostaCertaException  extends NegocioException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public QuizSemRespostaCertaException(String mensagem) {
		// TODO Auto-generated constructor stub
		super(mensagem);
	}

}
