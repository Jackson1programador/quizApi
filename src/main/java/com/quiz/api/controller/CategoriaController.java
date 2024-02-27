package com.quiz.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.quiz.api.assembler.CategoriaModelAssembler;
import com.quiz.api.assembler.CategoriaModelDesassembler;
import com.quiz.api.model.CategoriaDTO;
import com.quiz.api.model.input.CategoriaInputDTO;
import com.quiz.domain.model.Categoria;
import com.quiz.domain.service.CadastroCategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CadastroCategoriaService cadastroCategoria;
	
	@Autowired
	private CategoriaModelAssembler categoriaModelAssembler;
	
	@Autowired
	private CategoriaModelDesassembler categoriaModelDesassembler;
	
	@GetMapping()
	public List<CategoriaDTO> list() {
		List<CategoriaDTO> list = categoriaModelAssembler.toCollectionModel(cadastroCategoria.list()); 
		return list;
	}
	
	
	@GetMapping("/{categoriaId}")
	public CategoriaDTO buscaPorId( @PathVariable Long categoriaId) {
		Categoria categoria = cadastroCategoria.buscarOuFalhar(categoriaId);
		CategoriaDTO categoriaDTO = new CategoriaDTO();
		categoriaDTO = categoriaModelAssembler.toModel(categoria);
		return categoriaDTO;
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public CategoriaDTO incluir( @RequestBody @Valid CategoriaInputDTO categoriaInputDTO) {
		Categoria categoria = categoriaModelDesassembler.toDomainObject(categoriaInputDTO);
		return categoriaModelAssembler.toModel(cadastroCategoria.salvar(categoria));
	}
	
	
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long categoriaId) {
			cadastroCategoria.excluir(categoriaId);
		
	}
	
	@PutMapping("/{categoriaId}")
	public CategoriaDTO atualizar(@PathVariable Long categoriaId, @RequestBody @Valid CategoriaInputDTO categoriaDTO ){
		Categoria categoriaAtualizado = cadastroCategoria.buscarOuFalhar(categoriaId);
		// {
		//Categoria categoria = categoriaModelDesassembler.toDomainObject(categoriaDTO);
		//BeanUtils.copyProperties(categoria, categoriaAtualizado, "id", "dataCadastro");
		categoriaModelDesassembler.copyToDomainObject(categoriaDTO, categoriaAtualizado);
		// }
		return categoriaModelAssembler.toModel(cadastroCategoria.salvar(categoriaAtualizado));
	}	
	
	
}
