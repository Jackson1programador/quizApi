package com.quiz.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.CategoriaDTO;
import com.quiz.domain.model.Categoria;

@Component
public class CategoriaModelAssembler {
	
	@Autowired
	ModelMapper modelMapper;

	public CategoriaDTO toModel(Categoria categoria) {
		//CategoriaDTO categoriaDTO = new CategoriaDTO();
		//categoriaDTO.setNome(categoria.getNome());
		//categoriaDTO.setId(categoria.getId());
		//categoriaDTO.setDescricao(categoria.getDescricao());
		//categoriaDTO.setFoto(categoria.getFoto());
		//return categoriaDTO;
		
		return modelMapper.map(categoria, CategoriaDTO.class);
	}
	
	public List<CategoriaDTO> toCollectionModel (List<Categoria> categorias) {
		return categorias.stream()
				.map(categoria -> toModel(categoria))
				.collect(Collectors.toList());
	}
	
}
