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

import com.quiz.api.assembler.PermissaoModelAssembler;
import com.quiz.api.assembler.PermissaoModelDesassembler;
import com.quiz.api.model.CategoriaDTO;
import com.quiz.api.model.PermissaoDTO;
import com.quiz.api.model.input.CategoriaInputDTO;
import com.quiz.api.model.input.PermissaoInputDTO;
import com.quiz.domain.model.Categoria;
import com.quiz.domain.model.Permissao;
import com.quiz.domain.service.CadastroPermissaoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/permissao")
public class PermissaoController {

	@Autowired
	private PermissaoModelDesassembler permissaoModelDesassembler;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping()
	public List<PermissaoDTO> list() {
		List<PermissaoDTO> list = permissaoModelAssembler.toCollectionModel(cadastroPermissaoService.list()); 
		return list;
	}
	
	
	@GetMapping("/{permissaoId}")
	public PermissaoDTO buscaPorId( @PathVariable Long permissaoId) {
		Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		PermissaoDTO permissaoDTO = new PermissaoDTO();
		permissaoDTO = permissaoModelAssembler.toModel(permissao);
		return permissaoDTO;
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public PermissaoDTO incluir( @RequestBody @Valid PermissaoInputDTO permissaoInputDTO) {
		Permissao permissao = permissaoModelDesassembler.toDomainObject(permissaoInputDTO);
		return permissaoModelAssembler.toModel(cadastroPermissaoService.salvar(permissao));
	}
	
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long permissaoId) {
		cadastroPermissaoService.excluir(permissaoId);
	}
	
	
	@PutMapping("/{permissaoId}")
	public PermissaoDTO atualizar(@PathVariable Long permissaoId, @RequestBody @Valid PermissaoInputDTO permissaoInputDTO ){
		Permissao PermissaoAtualizado = cadastroPermissaoService.buscarOuFalhar(permissaoId);
		permissaoModelDesassembler.copyToDomainObject(permissaoInputDTO, PermissaoAtualizado);
		return permissaoModelAssembler.toModel(cadastroPermissaoService.salvar(PermissaoAtualizado));
	}	
}
