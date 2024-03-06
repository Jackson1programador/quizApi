package com.quiz.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.PermissaoDTO;
import com.quiz.domain.model.Permissao;

@Component
public class PermissaoModelAssembler {

	// tenho que usar o permissãoDTO no momneto da uma cópia da categoriaModelAssembler
	@Autowired
	ModelMapper modelMapper;
	
	public PermissaoDTO toModel(Permissao permissao) {
		
		return modelMapper.map(permissao, PermissaoDTO.class);
	}
	
	public List<PermissaoDTO> toCollectionModel (List<Permissao> permissoes) {
		return permissoes.stream()
				.map(permissao -> toModel(permissao))
				.collect(Collectors.toList());
	}
}
