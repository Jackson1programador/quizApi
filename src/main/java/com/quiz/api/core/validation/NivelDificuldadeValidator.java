package com.quiz.api.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NivelDificuldadeValidator implements ConstraintValidator<NivelDificuldade, Integer>{

	private int nivelDificuldadeMinima;
	private int nivelDificuldadeMaxima;

	
	@Override
	public void initialize(NivelDificuldade constraintAnnotation) {
		this.nivelDificuldadeMinima = constraintAnnotation.min();
		this.nivelDificuldadeMaxima = constraintAnnotation.max();
		
	}
	
	
	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		boolean valido = true;
		
		if(value != null) {
			var nivelDificuldade = value;
			valido = nivelDificuldade >= this.nivelDificuldadeMinima && nivelDificuldade <= this.nivelDificuldadeMaxima;
		}
				
		return valido;
	}
	
	
	

}
