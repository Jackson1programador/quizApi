package com.quiz.api.model;

import com.quiz.domain.model.AlternativaDaResposta;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizSomenteRespostaCertaDTO {

	private Long id;
	
	private AlternativaDaResposta respostaCerta;

}
