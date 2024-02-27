package com.quiz.api.model.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaInputDTOPorId {

	@NotNull
	private Long id;
}
