package com.quiz.api.model.input;

import java.util.List;


import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GrupoInputDTO {

	@NotBlank
	private String nome;
	
	private List<PermissaoInputDTOPorId> permissoes;
}
