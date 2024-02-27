package com.quiz.api.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoriaDTO {

	private Long id;
	
	private String nome;
	
	private String descricao;

	private String foto;
}
