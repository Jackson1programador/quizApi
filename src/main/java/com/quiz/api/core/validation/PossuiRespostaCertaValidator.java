package com.quiz.api.core.validation;

import org.springframework.beans.BeanUtils;

import com.quiz.domain.model.AlternativaDaResposta;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;

public class PossuiRespostaCertaValidator implements ConstraintValidator<PossuiRespostaCerta, Object>{
	
	private String alternativaA;
	private String alternativaB;
	private String alternativaC;
	private String alternativaD;
	private String alternativaE;
	private String respostaCerta;

	@Override
	public void initialize(PossuiRespostaCerta constraintAnnotation) {
		this.alternativaA = constraintAnnotation.alternativaA();
		this.alternativaB = constraintAnnotation.alternativaB();
		this.alternativaC = constraintAnnotation.alternativaC();
		this.alternativaD = constraintAnnotation.alternativaD();
		this.alternativaE = constraintAnnotation.alternativaE();
		this.respostaCerta = constraintAnnotation.respostaCerta();
	}
	
	@Override
	public boolean isValid(Object objetoValidacao, ConstraintValidatorContext context) {
		boolean valido = true;
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
			AlternativaDaResposta respostaCertaId =  (AlternativaDaResposta) BeanUtils.getPropertyDescriptor(objetoValidacao.getClass(), respostaCerta)
					.getReadMethod().invoke(objetoValidacao);
			
			Long idA = alternativaAId.getId();
			Long idB = alternativaBId.getId();
			Long idC = alternativaCId.getId();
			Long idD = alternativaDId.getId();
			Long idE = alternativaEId.getId();
			Long idCerto = respostaCertaId.getId();
			
			if(	idCerto != idA && idCerto != idB &&	idCerto != idC && idCerto != idD &&	idCerto != idE ) {
				valido = false;
			}
			
			return valido;
		} catch (Exception e) {
			throw new ValidationException(e);
		}
		
		
	}
}
