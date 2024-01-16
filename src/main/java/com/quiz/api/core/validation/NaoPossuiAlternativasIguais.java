package com.quiz.api.core.validation;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.ElementType.TYPE_USE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ TYPE, TYPE_USE })
@Constraint(validatedBy = {NaoPossuiAlternativasIguaisValidator.class })
public @interface NaoPossuiAlternativasIguais {

	String message() default "Alguma das alternativas estão iguais";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String alternativaA();
	String alternativaB();
	String alternativaC();
	String alternativaD();
	String alternativaE();
}
