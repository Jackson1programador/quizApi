package com.quiz.domain.model;

import java.util.ArrayList;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String categoria;
	
	@Column(nullable = false)
	private String dificuldade;
	
	private String pergunta;
	
	private ArrayList<String> alternativas;
	
	private String respostaCerta;
	
}
