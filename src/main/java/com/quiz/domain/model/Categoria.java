package com.quiz.domain.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Data
@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	private String descricao;

	private String foto;
	
	@JsonIgnore
	@OneToMany(mappedBy = "categoria")
	private List<Quiz> perguntas = new ArrayList<>();
	
	@JsonIgnore
	@CreationTimestamp
	@Column(name = "data_cadastro", columnDefinition = "datetime")
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "data_atualizacao", columnDefinition = "datetime")
	private LocalDateTime dataAtualizaçao;
}
