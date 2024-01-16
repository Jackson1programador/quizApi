package com.quiz.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
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


import com.quiz.domain.model.Categoria;
import com.quiz.domain.service.CadastroCategoriaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	
	@Autowired
	private CadastroCategoriaService cadastroCategoria;
	
	
	@GetMapping()
	public List<Categoria> list() {
		List<Categoria> list = cadastroCategoria.list();
		return list;
	}
	
	
	@GetMapping("/{categoriaId}")
	public Categoria buscaPorId( @PathVariable Long categoriaId) {
		return cadastroCategoria.buscarOuFalhar(categoriaId);
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public Categoria incluir( @RequestBody @Valid Categoria categoria) {
		return cadastroCategoria.salvar(categoria);	
	}
	
	
	@DeleteMapping("/{categoriaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long categoriaId) {
			cadastroCategoria.excluir(categoriaId);
		
	}
	
	
	@PutMapping("/{categoriaId}")
	public Categoria atualizar(@PathVariable Long categoriaId, @RequestBody @Valid Categoria categoria ){
		Categoria categoriaAtualizado = cadastroCategoria.buscarOuFalhar(categoriaId);
		BeanUtils.copyProperties(categoria, categoriaAtualizado, "id", "dataCadastro");
		return cadastroCategoria.salvar(categoriaAtualizado);
	}	
	
	
	
	
	

}
