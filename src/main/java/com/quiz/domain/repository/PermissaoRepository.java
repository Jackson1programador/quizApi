package com.quiz.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.domain.model.Permissao;

public interface PermissaoRepository extends JpaRepository<Permissao, Long>{

}
