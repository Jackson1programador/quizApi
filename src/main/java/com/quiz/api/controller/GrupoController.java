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

import com.quiz.api.assembler.GrupoModelAssembler;
import com.quiz.api.assembler.GrupoModelDesassembler;
import com.quiz.api.model.GrupoDTO;
import com.quiz.api.model.input.GrupoInputDTO;
import com.quiz.domain.model.Grupo;
import com.quiz.domain.service.CadastroGrupoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/grupo")
public class GrupoController {

	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoModelDesassembler grupoModelDesassembler;
	
	@GetMapping()
	public List<GrupoDTO> list() {
		List<GrupoDTO> list = grupoModelAssembler.toCollectionModel(cadastroGrupoService.list()); 
		return list;
	}
	
	
	@GetMapping("/{grupoId}")
	public GrupoDTO buscaPorId( @PathVariable Long grupoId) {
		Grupo grupo = cadastroGrupoService.buscarOuFalhar(grupoId);
		GrupoDTO grupoDTO = new GrupoDTO();
		grupoDTO = grupoModelAssembler.toModel(grupo);
		return grupoDTO;
	}
	
	// preciso corrigir pq minha resposta para o cliente está vindo com o id da permissao correta, porem os atributos vem nulo
	// mas quando eu consulto fazendo um get, vem correto.
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public GrupoDTO incluir( @RequestBody @Valid GrupoInputDTO grupoInputDTO) {
		Grupo grupo = grupoModelDesassembler.toDomainObject(grupoInputDTO);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupo));
	}
	
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long grupoId) {
		cadastroGrupoService.excluir(grupoId);
	}
	
	
	@PutMapping("/{grupoId}")
	public GrupoDTO atualizar(@PathVariable Long grupoId, @RequestBody @Valid GrupoInputDTO grupoInputDTO ){
		Grupo grupoAtualizado = cadastroGrupoService.buscarOuFalhar(grupoId);
		grupoModelDesassembler.copyToDomainObject(grupoInputDTO, grupoAtualizado);
		return grupoModelAssembler.toModel(cadastroGrupoService.salvar(grupoAtualizado));
	}	
}
