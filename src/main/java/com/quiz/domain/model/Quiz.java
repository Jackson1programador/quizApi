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
	@JoinColumn(name = "alternativa_id_a")
	private AlternativaDaResposta alternativaA;
	
	@ManyToOne
	@JoinColumn(name = "alternativa_id_b")
	private AlternativaDaResposta alternativaB;
	
	@ManyToOne
	@JoinColumn(name = "alternativa_id_c")
	private AlternativaDaResposta alternativaC;
	
	@ManyToOne
	@JoinColumn(name = "alternativa_id_d")
	private AlternativaDaResposta alternativaD;
	
	@ManyToOne
	@JoinColumn(name = "alternativa_id_e")
	private AlternativaDaResposta alternativaE;
	
	@ManyToOne
	@JoinColumn(name = "resposta_certa_id")
	private AlternativaDaResposta respostaCerta;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "data_cadastro", columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "data_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizaçao;
	
}
