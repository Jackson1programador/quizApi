package com.quiz.api.core.validation;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Retention(RUNTIME)
@Target({ ElementType.TYPE })
@Constraint(validatedBy = {PossuiRespostaCertaValidator.class })
public @interface PossuiRespostaCerta {

	String message() default "Nenhuma das alternativas possui a resposta certa";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
	
	String alternativaA();
	String alternativaB();
	String alternativaC();
	String alternativaD();
	String alternativaE();
	String respostaCerta();
}
