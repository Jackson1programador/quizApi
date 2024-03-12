package com.quiz.api.model.input;

import java.util.List;

import com.quiz.api.model.GrupoDTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDTO {

	private Long id;

	private String nome;
	
	private String email;
	
	private List<GrupoDTO> grupos;
}
