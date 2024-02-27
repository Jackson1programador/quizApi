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

import com.quiz.api.assembler.AlternativaDaRespostaModelAssembler;
import com.quiz.api.assembler.AlternativaDaRespostaModelDesassembler;
import com.quiz.api.model.AlternativaDaRespostaDTO;
import com.quiz.api.model.input.AlternativaDaRespostaInputDTO;
import com.quiz.domain.model.AlternativaDaResposta;
import com.quiz.domain.service.CadastroAlternativaDaRespostaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/alternativaDaResposta")
public class AlternativaDaRespostaController {
	
	@Autowired
	private CadastroAlternativaDaRespostaService cadastroAlternativaDaResposta;
	
	@Autowired
	private AlternativaDaRespostaModelAssembler alternativaDaRespostaModelAssembler;
	
	@Autowired
	private AlternativaDaRespostaModelDesassembler alternativaDaRespostaModelDesassembler;
	
	
	@GetMapping()
	public List<AlternativaDaResposta> list() {
		List<AlternativaDaResposta> list = cadastroAlternativaDaResposta.list();
		return list;
	}
	
	
	@GetMapping("/{alternativaDaRespostaId}")
	public AlternativaDaRespostaDTO buscaPorId( @PathVariable Long alternativaDaRespostaId) {
		AlternativaDaResposta alternativaDaResposta = cadastroAlternativaDaResposta.buscarOuFalhar(alternativaDaRespostaId);
		AlternativaDaRespostaDTO alternativaDaRespostaDTO = alternativaDaRespostaModelAssembler.toModel(alternativaDaResposta);
		return alternativaDaRespostaDTO;
	}
	//public AlternativaDaResposta buscaPorId( @PathVariable Long alternativaDaRespostaId) {
	//	return cadastroAlternativaDaResposta.buscarOuFalhar(alternativaDaRespostaId);
	//}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlternativaDaRespostaDTO incluir( @RequestBody @Valid AlternativaDaRespostaInputDTO alternativaDaRespostaInputDTO) {
		AlternativaDaResposta alternativaDaResposta = alternativaDaRespostaModelDesassembler.toDomainObject(alternativaDaRespostaInputDTO);
		return alternativaDaRespostaModelAssembler.toModel(cadastroAlternativaDaResposta.salvar(alternativaDaResposta));
	}
	
	
	@DeleteMapping("/{alternativaDaRespostaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long alternativaDaRespostaId) {
		cadastroAlternativaDaResposta.excluir(alternativaDaRespostaId);
		
	}
	
	
	@PutMapping("/{alternativaDaRespostaId}")
	public AlternativaDaRespostaDTO atualizar(@PathVariable Long alternativaDaRespostaId, @RequestBody @Valid AlternativaDaRespostaInputDTO	alternativaDaRespostaInputDTO ){
		AlternativaDaResposta alternativaDaRespostaAtualizado = cadastroAlternativaDaResposta.buscarOuFalhar(alternativaDaRespostaId);
		alternativaDaRespostaModelDesassembler.copyToDomainObject(alternativaDaRespostaInputDTO, alternativaDaRespostaAtualizado);
		return alternativaDaRespostaModelAssembler.toModel(cadastroAlternativaDaResposta.salvar(alternativaDaRespostaAtualizado));
	}	

}
