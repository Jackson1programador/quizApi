package com.quiz.api.exceptionHandler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.quiz.domain.exception.EntidadeNãoEcontradaException;
import com.quiz.domain.exception.NegocioException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@ExceptionHandler(EntidadeNãoEcontradaException.class)
	public ResponseEntity<?> handlerEntidadeNãoEcontradaException(EntidadeNãoEcontradaException e, WebRequest request){
		HttpStatus status = HttpStatus.NOT_FOUND;
		String detail = e.getMessage();
		
		Problem problem = Problem.builder()
				.status(status.value())
				.type("https//quiz.com.br/entidade-nao-encontrada")
				.title("Entidade não encontrada")
				.detail(detail)
				.build();
				
		return handleExceptionInternal(e, problem, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
	}
	
	@ExceptionHandler(NegocioException.class)
	public ResponseEntity<?> handlerNegocioException(NegocioException e, WebRequest request){
		return handleExceptionInternal(e, e.getMessage(), new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
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
	
	
	
}
