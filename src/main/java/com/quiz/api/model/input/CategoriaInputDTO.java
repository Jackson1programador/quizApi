package com.quiz.api.model.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInputDTO {

	@NotBlank
	private String nome;
	@NotBlank
	private String descricao;

	private String foto;
}
