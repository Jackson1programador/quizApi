package com.quiz.domain.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.quiz.Groups;
import com.quiz.api.core.validation.NaoPossuiAlternativasIguais;
import com.quiz.api.core.validation.NivelDificuldade;
import com.quiz.api.core.validation.PossuiRespostaCerta;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.ConvertGroup;
import jakarta.validation.groups.Default;
import lombok.Data;

@PossuiRespostaCerta(alternativaA = "alternativaA", alternativaB = "alternativaB", 
alternativaC = "alternativaC", alternativaD = "alternativaD", alternativaE = "alternativaE", respostaCerta = "respostaCerta" )
@NaoPossuiAlternativasIguais(alternativaA = "alternativaA", alternativaB = "alternativaB", 
alternativaC = "alternativaC", alternativaD = "alternativaD", alternativaE = "alternativaE")
@Data
@Entity
public class Quiz {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Valid
	@NotNull
	@ConvertGroup(from = Default.class, to = Groups.CategoriaId.class)
	@ManyToOne
	@JoinColumn(name = "categoria_id", nullable = false )
	private Categoria categoria;
	
	@NivelDificuldade
	@Column(nullable = false)
	private int dificuldade;
	
	@NotBlank
	private String pergunta;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "alternativa_id_a")
	private AlternativaDaResposta alternativaA;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "alternativa_id_b")
	private AlternativaDaResposta alternativaB;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "alternativa_id_c")
	private AlternativaDaResposta alternativaC;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "alternativa_id_d")
	private AlternativaDaResposta alternativaD;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name = "alternativa_id_e")
	private AlternativaDaResposta alternativaE;
	
	@NotNull
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
