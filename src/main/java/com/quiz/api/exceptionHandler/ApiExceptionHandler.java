package com.quiz.api.exceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
import com.quiz.domain.exception.GrupoNaoEncontradoExecption;
import com.quiz.domain.exception.NegocioException;
import com.quiz.domain.exception.PermissaoNaoEncontradaException;
import com.quiz.domain.exception.QuizAlternativaIguaisException;
import com.quiz.domain.exception.QuizDificuldadeValoresNaoAceitoException;
import com.quiz.domain.exception.QuizSemRespostaCertaException;
import com.quiz.domain.exception.UsuarioNaoEncontradoException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{
	
	@Autowired
	private MessageSource messageSource;
	
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
	
	@ExceptionHandler(PermissaoNaoEncontradaException.class)
	public ResponseEntity<?> handlerPermissaoNãoEcontradaException(
			PermissaoNaoEncontradaException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.PERMISSAO_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(GrupoNaoEncontradoExecption.class)
	public ResponseEntity<?> handlerGrupoNãoEcontradaException(
			GrupoNaoEncontradoExecption e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.GRUPO_NAO_ENCONTRADA;
		Problem problem = createProblemBuilder(status, problemType, detail).build();		
		return handleExceptionInternal(e, problem, new HttpHeaders(), status, request);		
	}
	
	@ExceptionHandler(UsuarioNaoEncontradoException.class)
	public ResponseEntity<?> handlerUsuarioNãoEcontradaException(
			UsuarioNaoEncontradoException e, WebRequest request){
		
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		ProblemType problemType = ProblemType.USUARIO_NAO_ENCONTRADA;
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
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		String detail = "Um ou mais campos estão inválidos. Faça o preenchimento correto e tente novamente.";
		ProblemType problemType = ProblemType.DADOS_INVALIDOS;
		BindingResult bindingResult = ex.getBindingResult();
		
		List<Problem.Field> problemFields = bindingResult.getAllErrors().stream()
				.map(objectErro -> {
					String message = messageSource.getMessage(objectErro, LocaleContextHolder.getLocale());
				
					String name = objectErro.getObjectName();
					
					if (objectErro instanceof FieldError) {
						name = ((FieldError) objectErro).getField();
					}
							
					return	Problem.Field.builder()
						.name(name)
						.userMensage(message)
						.build();
						})
				.collect(Collectors.toList());
		
		
		Problem problem = createProblemBuilder(status, problemType, detail)
				.userMensage(detail)
				.Fields(problemFields)
		        .build();
		
		return handleExceptionInternal(ex, problem, headers, status, request);
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
		
		return Problem.builder()
				.timestamp(LocalDateTime.now())
				.status(status.value())
				.type(problemType.getUri())
				.title(problemType.getTitle())
				.detail(detail);
	}
	
}
