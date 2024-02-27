package com.quiz.api.controller;

import java.util.List;

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

import com.quiz.api.assembler.QuizModelAssembler;
import com.quiz.api.assembler.QuizModelDesassembler;
import com.quiz.api.model.QuizDTO;
import com.quiz.api.model.QuizSemRespostaCertaDTO;
import com.quiz.api.model.QuizSomenteRespostaCertaDTO;
import com.quiz.api.model.input.QuizInputDTO;
import com.quiz.domain.exception.AlternativaDaRespostaNãoEcontradaException;
import com.quiz.domain.exception.AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException;
import com.quiz.domain.exception.CategoriaNãoEcontradaException;
import com.quiz.domain.exception.CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException;
import com.quiz.domain.model.Quiz;
import com.quiz.domain.service.CadastroQuizService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/quiz")
public class QuizController {
	
	@Autowired
	private CadastroQuizService cadastroQuiz;
	
	@Autowired
	private QuizModelAssembler quizModelAssembler;
	
	@Autowired
	private QuizModelDesassembler quizModelDesassembler;
	
	@GetMapping()
	public List<QuizDTO> list() {
		List<QuizDTO> list = quizModelAssembler.toCollectionModel(cadastroQuiz.list()) ;
		return list;
	}
	
	
	@GetMapping("/{quizId}")
	public QuizDTO buscaPorId( @PathVariable Long quizId) {
		return quizModelAssembler.toModel(cadastroQuiz.buscarOuFalhar(quizId));
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public QuizDTO incluir( @RequestBody @Valid QuizInputDTO quizInputDTO) {
		try {
			Quiz quiz = quizModelDesassembler.toDomainObject(quizInputDTO);
			return quizModelAssembler.toModel(cadastroQuiz.salvar(quiz));
		} catch (CategoriaNãoEcontradaException e) {
			throw new CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(e.getMessage(), e);
		} catch (AlternativaDaRespostaNãoEcontradaException e) {
			throw new AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(e.getMessage(), e);
		}
		
	}
	
	
	@DeleteMapping("/{quizId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long quizId) {
			cadastroQuiz.excluir(quizId);
		
	}
	
	
	@PutMapping("/{quizId}")
	public QuizDTO atualizar(@PathVariable Long quizId, @RequestBody @Valid QuizInputDTO quizInputDTO ){
		
		try {
			Quiz quizAtualizado = cadastroQuiz.buscarOuFalhar(quizId);
			quizModelDesassembler.copyToDomainObject(quizInputDTO, quizAtualizado);
			return quizModelAssembler.toModel(cadastroQuiz.salvar(quizAtualizado));
		} catch (CategoriaNãoEcontradaException e) {
			throw new CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(e.getMessage(), e);
		} catch (AlternativaDaRespostaNãoEcontradaException e) {
			throw new AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(e.getMessage(), e);
		}
	}	
	

	@GetMapping("/{quizId}/sem-resposta-certa")
	public QuizSemRespostaCertaDTO buscaPorIdSemRespostaCerta( @PathVariable Long quizId) {
		return quizModelAssembler.toModelSemRespostaCerta(cadastroQuiz.buscarOuFalhar(quizId));
	}

	@GetMapping("/{quizId}/somente-resposta-certa")
	public QuizSomenteRespostaCertaDTO buscaPorIdSomenteRespostaCerta( @PathVariable Long quizId) {
		return quizModelAssembler.toModelSomenteRespostaCerta(cadastroQuiz.buscarOuFalhar(quizId));
	}
}
	