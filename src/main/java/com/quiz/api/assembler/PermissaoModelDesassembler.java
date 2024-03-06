package com.quiz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.PermissaoInputDTO;
import com.quiz.domain.model.Permissao;

@Component
public class PermissaoModelDesassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Permissao toDomainObject (PermissaoInputDTO permissaoInputDTO) {		
		return modelMapper.map(permissaoInputDTO, Permissao.class);
	}

	public void copyToDomainObject(PermissaoInputDTO permissaoInputDTO, Permissao permissao) {
		modelMapper.map(permissaoInputDTO, permissao);
	}
}
