package com.quiz.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.domain.model.Quiz;
import com.quiz.domain.service.CadastroQuizService;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private CadastroQuizService cadastroQuiz;
	
	
	@GetMapping()
	public List<Quiz> list() {
		List<Quiz> list = cadastroQuiz.list();
		return list;
	}
	
	
	@GetMapping("/{quizId}")
	public Quiz buscaPorId( @PathVariable Long quizId) {
		return cadastroQuiz.buscarOuFalhar(quizId);
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public Quiz incluir( @RequestBody Quiz quiz) {
		return cadastroQuiz.salvar(quiz);
	}
	
	
	@DeleteMapping("/{quizId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long quizId) {
			cadastroQuiz.excluir(quizId);
		
	}
	
	
	@PutMapping("/{quizId}")
	public Quiz atualizar(@PathVariable Long quizId, @RequestBody Quiz quiz ){
		Quiz quizAtualizado = cadastroQuiz.buscarOuFalhar(quizId);
		BeanUtils.copyProperties(quiz, quizAtualizado, "id");
		return cadastroQuiz.salvar(quizAtualizado);
		
		
	}	
	
}
	