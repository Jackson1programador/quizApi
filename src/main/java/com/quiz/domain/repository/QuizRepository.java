package com.quiz.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quiz.domain.model.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {

	

}
