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

import com.quiz.api.assembler.UsuarioModelAssembler;
import com.quiz.api.assembler.UsuarioModelDesassembler;
import com.quiz.api.model.input.UsuarioDTO;
import com.quiz.api.model.input.UsuarioInputDTO;
import com.quiz.domain.model.Usuario;
import com.quiz.domain.service.CadastroUsuarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
	
	@Autowired
	private UsuarioModelDesassembler usuarioModelDesassembler;
	
	@GetMapping()
	public List<UsuarioDTO> list() {
		List<UsuarioDTO> list = usuarioModelAssembler.toCollectionModel(cadastroUsuarioService.list()); 
		return list;
	}
	
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscaPorId( @PathVariable Long usuarioId) {
		Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO = usuarioModelAssembler.toModel(usuario);
		return usuarioDTO;
	}
	
	
	//quero que o retorno não venha com informações null
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public UsuarioDTO incluir( @RequestBody @Valid UsuarioInputDTO usuarioInputDTO) {
		Usuario usuario = usuarioModelDesassembler.toDomainObject(usuarioInputDTO);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long usuarioId) {
		cadastroUsuarioService.excluir(usuarioId);
	}
	
	
	@PutMapping("/{usuarioId}")
	public UsuarioDTO atualizar(@PathVariable Long usuarioId, @RequestBody @Valid UsuarioInputDTO usuarioInputDTO ){
		Usuario usuarioAtualizado = cadastroUsuarioService.buscarOuFalhar(usuarioId);
		usuarioModelDesassembler.copyToDomainObject(usuarioInputDTO, usuarioAtualizado);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuarioAtualizado));
	}	
	
}
