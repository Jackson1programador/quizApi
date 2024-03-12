package com.quiz.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
