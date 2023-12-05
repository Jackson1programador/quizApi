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

import com.quiz.domain.model.AlternativaDaResposta;
import com.quiz.domain.service.CadastroAlternativaDaRespostaService;

@RestController
@RequestMapping("/alternativaDaResposta")
public class AlternativaDaRespostaController {
	
	@Autowired
	private CadastroAlternativaDaRespostaService cadastroAlternativaDaResposta;
	
	
	@GetMapping()
	public List<AlternativaDaResposta> list() {
		List<AlternativaDaResposta> list = cadastroAlternativaDaResposta.list();
		return list;
	}
	
	
	@GetMapping("/{alternativaDaRespostaId}")
	public AlternativaDaResposta buscaPorId( @PathVariable Long alternativaDaRespostaId) {
		return cadastroAlternativaDaResposta.buscarOuFalhar(alternativaDaRespostaId);
	}
	
	
	@PostMapping()
	@ResponseStatus(value = HttpStatus.CREATED)
	public AlternativaDaResposta incluir( @RequestBody AlternativaDaResposta alternativaDaResposta) {
		return cadastroAlternativaDaResposta.salvar(alternativaDaResposta);
	}
	
	
	@DeleteMapping("/{alternativaDaRespostaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void deletar( @PathVariable Long alternativaDaRespostaId) {
		cadastroAlternativaDaResposta.excluir(alternativaDaRespostaId);
		
	}
	
	
	@PutMapping("/{alternativaDaRespostaId}")
	public AlternativaDaResposta atualizar(@PathVariable Long alternativaDaRespostaId, @RequestBody AlternativaDaResposta 	alternativaDaResposta ){
		AlternativaDaResposta alternativaDaRespostaAtualizado = cadastroAlternativaDaResposta.buscarOuFalhar(alternativaDaRespostaId);
		BeanUtils.copyProperties(alternativaDaResposta, alternativaDaRespostaAtualizado, "id", "dataCadastro");
		return cadastroAlternativaDaResposta.salvar(alternativaDaRespostaAtualizado);
	}	

}
