package com.quiz.api.exceptionHandler;

import lombok.Getter;

@Getter
public enum ProblemType {

	ENTIDADE_NAO_ENCONTRADA("/entidade-nao-encontrada", "Entidade não encontrada."),
	ENTIDADE_EM_USO("/entidade-em-uso", "Entidade em uso"),
	ERRO_NEGOCIO("/erro-negocio", "Erro de negócio"),
	ALTERNATIVAS_IGUAIS("/alternativas-iguais", "Alternativas iguais"),
	DADOS_INVALIDOS("/dados-inválidos", "Dados Inválidos"),
	SEM_RESPOSTA_CERTA("/quiz-sem-resposta-certa", "A quiz não possui uma resposta certa"),
	DIFICULDADE_NAO_ACEITA("/dificuldade-não-está-no-intervalo-de-1-a-5-ou-não-é-número-inteiro", "O valor dificuldade não está dentro do intervalo de 1 a 5 ou não é número inteiro"),
	CATEGORIA_NAO_ENCONTRADA("/categoria-não-encontrada-no-banco-de-dados", "Categoria não encontrada no banco de dados"),
	ALTERNATIVA_DA_RESPOSTA_NAO_ENCONTRADA("/alternativa-da-resposta-não-encontrada-no-banco-de-dados", "Alternativa da resposta não encontrada no banco de dados"),
	ERRO_SINTAXE("/erro-sintaxe", "Erro de sintaxe");
	
	
	private String title;
	private String uri;
	
	private ProblemType(String path, String title) {
		this.uri = "https://quiz.com.br" + path;
		this.title = title;
	}
}
