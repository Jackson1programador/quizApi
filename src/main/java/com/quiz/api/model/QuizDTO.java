package com.quiz.api.model;

import com.quiz.domain.model.AlternativaDaResposta;
import com.quiz.domain.model.Categoria;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizDTO {

	private Long id;
	
	private Categoria categoria;
	
	private int dificuldade;
	
	private String pergunta;
	
	private AlternativaDaResposta alternativaA;
	
	private AlternativaDaResposta alternativaB;
	
	private AlternativaDaResposta alternativaC;
	
	private AlternativaDaResposta alternativaD;
	
	private AlternativaDaResposta alternativaE;
	
	private AlternativaDaResposta respostaCerta;
}
