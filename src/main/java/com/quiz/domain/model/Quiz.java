package com.quiz.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false )
	private Categoria categoria;
	
	@Column(nullable = false)
	private int dificuldade;
	
	private String pergunta;
	
	@ManyToOne
	private AlternativaDaResposta alternativaA;
	
	@ManyToOne
	private AlternativaDaResposta alternativaB;
	
	@ManyToOne
	private AlternativaDaResposta alternativaC;
	
	@ManyToOne
	private AlternativaDaResposta alternativaD;
	
	@ManyToOne
	private AlternativaDaResposta alternativaE;
	
	@ManyToOne
	private AlternativaDaResposta respostaCerta;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(nullable = false, columnDefinition = "datetime")
	private LocalDateTime dataAtualizaçao;
	
}
