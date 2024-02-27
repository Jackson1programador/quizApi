package com.quiz.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.QuizDTO;
import com.quiz.api.model.QuizSemRespostaCertaDTO;
import com.quiz.api.model.QuizSomenteRespostaCertaDTO;
import com.quiz.domain.model.Quiz;

@Component
public class QuizModelAssembler {

	@Autowired
	ModelMapper modelMapper;
	
	public QuizDTO toModel(Quiz quiz) {
		return modelMapper.map(quiz, QuizDTO.class);
	}
	
	public List<QuizDTO> toCollectionModel (List<Quiz> quizs) {
		return quizs.stream()
				.map(quiz -> toModel(quiz))
				.collect(Collectors.toList());
	}
	
	
	
	
	public QuizSemRespostaCertaDTO toModelSemRespostaCerta(Quiz quiz) {
		return modelMapper.map(quiz, QuizSemRespostaCertaDTO.class);
	}
	
	public List<QuizSemRespostaCertaDTO> toCollectionModelSemRespostaCerta (List<Quiz> quizs) {
		return quizs.stream()
				.map(quiz -> toModelSemRespostaCerta(quiz))
				.collect(Collectors.toList());
	}
	
	
	
	public QuizSomenteRespostaCertaDTO toModelSomenteRespostaCerta(Quiz quiz) {
		return modelMapper.map(quiz, QuizSomenteRespostaCertaDTO.class);
	}
	
	public List<QuizSomenteRespostaCertaDTO> toCollectionModelSomenteRespostaCerta (List<Quiz> quizs) {
		return quizs.stream()
				.map(quiz -> toModelSomenteRespostaCerta(quiz))
				.collect(Collectors.toList());
	}
}
