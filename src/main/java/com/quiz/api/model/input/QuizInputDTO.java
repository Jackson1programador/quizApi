package com.quiz.api.model.input;

import com.quiz.api.core.validation.NivelDificuldade;
import com.quiz.domain.model.AlternativaDaResposta;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuizInputDTO {

	@Valid
	@NotNull
	private CategoriaInputDTOPorId categoria;
	
	@NivelDificuldade
	private int dificuldade;
	
	@NotBlank
	private String pergunta;
	
	@NotNull
	private AlternativaDaResposta alternativaA;
	
	@NotNull
	private AlternativaDaResposta alternativaB;
	
	@NotNull
	private AlternativaDaResposta alternativaC;
	
	@NotNull
	private AlternativaDaResposta alternativaD;
	
	@NotNull
	private AlternativaDaResposta alternativaE;
	
	@NotNull
	private AlternativaDaResposta respostaCerta;

}
