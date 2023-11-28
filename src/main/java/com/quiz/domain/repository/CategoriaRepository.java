package com.quiz.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.domain.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

}
