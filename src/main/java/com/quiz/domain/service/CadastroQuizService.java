package com.quiz.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.exception.EntidadeNãoEcontradaException;
import com.quiz.domain.exception.QuizAlternativaIguaisException;
import com.quiz.domain.exception.QuizDificuldadeValoresNaoAceitoException;
import com.quiz.domain.exception.QuizSemRespostaCertaException;
import com.quiz.domain.model.Quiz;
import com.quiz.domain.repository.QuizRepository;

@Service
public class CadastroQuizService {
	
	private static final String MSG_QUIZ_ALTERNATIVAS_IGUAIS = "Algumas das alternativas estão iguais, certifique que todas as alternativas sejam diferentes";
	private static final String MSG_QUIZ_DIFICULDADE_FORA_DO_INTERVALO = "Propriedade dificuldade com valores não permitidos. Valores permitidos são inteiros entre 1 e 5";
	private static final String MSG_QUIZ_EM_USO = "A pergunta de código %d não pode ser removida, pois está em uso";
	private static final String MSG_QUIZ_NAO_ENCONTRADA = "Não existe o cadastro da pergunta com esse código %d";
	private static final String MSG_QUIZ_RESPOSTA_CERTA_DIVERGE_DE_TODAS_ALTERNATIVAS = "A resposta certa não está igual a nenhuma das alternativas";
	
	@Autowired
	private QuizRepository quizRepository;
	
	
	public List<Quiz> list () {
		List<Quiz> list = quizRepository.findAll();
		return list;
	}
	
	
	public Optional<Quiz> buscaPorId (Long quizId) {
		Optional<Quiz> quiz = quizRepository.findById(quizId);
		return quiz;	
	}
	
	
	public Quiz salvar (Quiz quiz) {
		if(quiz.getDificuldade() > 0 && quiz.getDificuldade() < 6) {
			if(					
				quiz.getRespostaCerta().equals(quiz.getAlternativaA()) ||
				quiz.getRespostaCerta().equals(quiz.getAlternativaB()) ||
				quiz.getRespostaCerta().equals(quiz.getAlternativaC()) ||
				quiz.getRespostaCerta().equals(quiz.getAlternativaD()) ||
				quiz.getRespostaCerta().equals(quiz.getAlternativaE()) 
				) {
					if(
						!quiz.getAlternativaA().equals(quiz.getAlternativaB()) &&
						!quiz.getAlternativaA().equals(quiz.getAlternativaB()) &&
						!quiz.getAlternativaA().equals(quiz.getAlternativaC()) &&
						!quiz.getAlternativaA().equals(quiz.getAlternativaD()) &&
						!quiz.getAlternativaA().equals(quiz.getAlternativaE()) &&
						!quiz.getAlternativaB().equals(quiz.getAlternativaC()) &&
						!quiz.getAlternativaB().equals(quiz.getAlternativaD()) &&
						!quiz.getAlternativaB().equals(quiz.getAlternativaE()) &&
						!quiz.getAlternativaC().equals(quiz.getAlternativaD()) &&
						!quiz.getAlternativaC().equals(quiz.getAlternativaE()) &&
						!quiz.getAlternativaD().equals(quiz.getAlternativaE()) 
						) {
						return quizRepository.save(quiz);
					} throw new QuizAlternativaIguaisException(MSG_QUIZ_ALTERNATIVAS_IGUAIS);
			} throw new QuizSemRespostaCertaException(MSG_QUIZ_RESPOSTA_CERTA_DIVERGE_DE_TODAS_ALTERNATIVAS);
		} throw new QuizDificuldadeValoresNaoAceitoException(MSG_QUIZ_DIFICULDADE_FORA_DO_INTERVALO);
	}
	
	
	public void excluir (Long quizId) {
		try {
			if (!quizRepository.existsById(quizId)) {
				throw new EntidadeNãoEcontradaException(
						String.format(MSG_QUIZ_NAO_ENCONTRADA, quizId));
			}
			quizRepository.deleteById(quizId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_QUIZ_EM_USO, quizId));
		}
	}
	
	
	//No momento não está em uso
	public Optional<Quiz> atualizar ( Long quizId,  Quiz quiz ){
		Optional<Quiz> quizNoBancoDeDados = quizRepository.findById(quizId);
		if(quizNoBancoDeDados.isPresent()) {
			BeanUtils.copyProperties(quiz, quizNoBancoDeDados.get(), "id");
			quizRepository.save(quizNoBancoDeDados.get());
			return quizNoBancoDeDados;
		}
		return quizNoBancoDeDados;
	}		
	
	public Quiz buscarOuFalhar(Long quizId) {
		return buscaPorId(quizId).orElseThrow(() -> new EntidadeNãoEcontradaException(String.format(MSG_QUIZ_NAO_ENCONTRADA, quizId)));
	}
	

}
