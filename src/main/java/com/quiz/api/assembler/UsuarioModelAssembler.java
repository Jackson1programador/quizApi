package com.quiz.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.UsuarioDTO;
import com.quiz.domain.model.Usuario;

@Component
public class UsuarioModelAssembler {

	@Autowired
	ModelMapper modelMapper;

	public UsuarioDTO toModel(Usuario usuario) {		
		return modelMapper.map(usuario, UsuarioDTO.class);
	}
	
	public List<UsuarioDTO> toCollectionModel (List<Usuario> usuarios) {
		return usuarios.stream()
				.map(usuario -> toModel(usuario))
				.collect(Collectors.toList());
	}
}
