package com.quiz.api.core.validation;

import org.springframework.beans.BeanUtils;

import com.quiz.domain.model.AlternativaDaResposta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class NaoPossuiAlternativasIguaisValidator implements ConstraintValidator<NaoPossuiAlternativasIguais, Object>{
	
	private String alternativaA;
	private String alternativaB;
	private String alternativaC;
	private String alternativaD;
	private String alternativaE;
	
	@Override
	public void initialize(NaoPossuiAlternativasIguais constraintAnnotation) {
		this.alternativaA = constraintAnnotation.alternativaA();
		this.alternativaB = constraintAnnotation.alternativaB();
		this.alternativaC = constraintAnnotation.alternativaC();
		this.alternativaD = constraintAnnotation.alternativaD();
		this.alternativaE = constraintAnnotation.alternativaE();
		
	}
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		boolean valido = false;
		try {
			AlternativaDaResposta alternativaAId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), alternativaA)
					.getReadMethod().invoke(objetoValidacao);
			AlternativaDaResposta alternativaBId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), alternativaB)
					.getReadMethod().invoke(objetoValidacao);
			AlternativaDaResposta alternativaCId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), alternativaC)
					.getReadMethod().invoke(objetoValidacao);
			AlternativaDaResposta alternativaDId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), alternativaD)
					.getReadMethod().invoke(objetoValidacao);
			AlternativaDaResposta alternativaEId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), alternativaE)
					.getReadMethod().invoke(objetoValidacao);
			
			Long idA = alternativaAId.getId();
			Long idB = alternativaBId.getId();
			Long idC = alternativaCId.getId();
			Long idD = alternativaDId.getId();
			Long idE = alternativaEId.getId();
			
			if(idA != idB && idA != idC && idA != idD && idA != idE && idB != idC && idB != idD && idB != idE && idC != idD && idC != idE && idD != idE) {
				valido = true;
			}
			
			return valido;
		} catch (Exception e) {
			throw new ValidationException(e);
		}
		
	}

}
