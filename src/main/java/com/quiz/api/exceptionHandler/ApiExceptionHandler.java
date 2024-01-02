package com.quiz.api.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.quiz.domain.exception.AlternativaDaRespostaNãoEcontradaException;
import com.quiz.domain.exception.AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException;
import com.quiz.domain.exception.CategoriaNãoEcontradaException;
import com.quiz.domain.exception.CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException;
import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.exception.EntidadeNãoEcontradaException;
import com.quiz.domain.exception.NegocioException;
import com.quiz.domain.exception.QuizAlternativaIguaisException;
import com.quiz.domain.exception.QuizDificuldadeValoresNaoAceitoException;
import com.quiz.domain.exception.QuizSemRespostaCertaException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Override
	protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
			HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		
		HttpStatus statusHttp = HttpStatus.BAD_REQUEST;
		String detail = "O corpo da requisição está inválida. Verifique erro de sintaxe.";
		ProblemType problemType = ProblemType.ERRO_SINTAXE;
		Problem problem = createProblemBuilder(statusHttp, problemType, detail).build();		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
			}

	@ExceptionHandler(EntidadeNãoEcontradaException.class)
	public ResponseEntity<?> handlerEntidadeNãoEcontradaException(EntidadeNãoEcontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handlerNegocioException(NegocioException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ERRO_NEGOCIO;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
		//return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
	}
	
	@ExceptionHandler(EntidadeEmUsoException.class)
	public ResponseEntity<?> handlerEntidadeEmUsoException(EntidadeEmUsoException e, WebRequest request){
		
		HttpStatus status = HttpStatus.CONFLICT;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ENTIDADE_EM_USO;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(AlternativaDaRespostaNãoEcontradaException.class)
	public ResponseEntity<?> handlerAlternativaDaRespostaNãoEcontradaException(
			AlternativaDaRespostaNãoEcontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ALTERNATIVA_DA_RESPOSTA_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException.class)
	public ResponseEntity<?> handlerAlternativaDaRespostaNãoEcontradaNoCorpoDoQuizException(
			AlternativaDaRespostaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ALTERNATIVA_DA_RESPOSTA_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(CategoriaNãoEcontradaException.class)
	public ResponseEntity<?> handlerCategoriaNãoEcontradaException(
			CategoriaNãoEcontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.CATEGORIA_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException.class)
	public ResponseEntity<?> handlerCategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException(
			CategoriaNãoEcontradaNoCorpoDaRequisiçãoDoQuizException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.CATEGORIA_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	
	@ExceptionHandler(QuizAlternativaIguaisException.class)
	public ResponseEntity<?> handlerQuizAlternativaIguaisException(
			QuizAlternativaIguaisException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.ALTERNATIVAS_IGUAIS;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	
	@ExceptionHandler(QuizDificuldadeValoresNaoAceitoException.class)
	public ResponseEntity<?> handlerQuizDificuldadeValoresNaoAceitoException(
			QuizDificuldadeValoresNaoAceitoException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.DIFICULDADE_NAO_ACEITA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	
	@ExceptionHandler(QuizSemRespostaCertaException.class)
	public ResponseEntity<?> handlerQuizSemRespostaCertaException(
			QuizSemRespostaCertaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.SEM_RESPOSTA_CERTA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	
	
	@Override
	protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
			HttpStatusCode statusCode, WebRequest request) {
		if(body == null){
			body = Problem.builder().title(ex.getMessage()).status(statusCode.value()).build();
		} else if (body instanceof String){
			body = Problem.builder().title((String) body).status(statusCode.value()).build();
		}
		
		return super.handleExceptionInternal(ex, body, headers, statusCode, request);
	}
	
	
	private Problem.ProblemBuilder createProblemBuilder(HttpStatus status, ProblemType problemType, String detail){
		
		return Problem.builder().status(status.value()).type(problemType.getUri()).title(problemType.getTitle()).detail(detail);
	}
	
}
