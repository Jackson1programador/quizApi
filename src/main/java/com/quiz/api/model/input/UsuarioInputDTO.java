package com.quiz.api.model.input;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioInputDTO {

	@NotBlank
	private String nome;
	
	@NotBlank
	private String email;
	
	@NotBlank
	private String senha;
	
	private List<GrupoInputDTOPorId> grupos;
}
