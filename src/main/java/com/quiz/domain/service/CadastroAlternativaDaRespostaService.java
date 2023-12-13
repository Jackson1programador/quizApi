package com.quiz.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.quiz.domain.exception.AlternativaDaRespostaNãoEcontradaException;
import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.model.AlternativaDaResposta;
import com.quiz.domain.model.Categoria;
import com.quiz.domain.repository.AlternativaDaRespostaRepository;

@Service
public class CadastroAlternativaDaRespostaService {

	private static final String MSG_ALTERNATIVA_EM_USO = "A alternativa de código %d não pode ser removida, pois está em uso";
	private static final String MSG_ALTERNATIVA_NAO_ENCONTRADA = "Não existe o cadastro da alternativa com esse código %d";
	@Autowired
	private AlternativaDaRespostaRepository alternativaDaRespostaRepository;
	
	public List<AlternativaDaResposta> list () {
		List<AlternativaDaResposta> list = alternativaDaRespostaRepository.findAll();
		return list;
	}
	
	
	public Optional<AlternativaDaResposta> buscaPorId (Long alternativaDaRespostaId) {
		Optional<AlternativaDaResposta> alternativaDaResposta = alternativaDaRespostaRepository.findById(alternativaDaRespostaId);
		return alternativaDaResposta;
	}
	
	
	public AlternativaDaResposta salvar (AlternativaDaResposta alternativaDaResposta) {
		return alternativaDaRespostaRepository.save(alternativaDaResposta);
	}
	
	
	public void excluir (Long alternativaDaRespostaId) {
		try {
			if (!alternativaDaRespostaRepository.existsById(alternativaDaRespostaId)) {
				throw new AlternativaDaRespostaNãoEcontradaException(
						String.format(MSG_ALTERNATIVA_NAO_ENCONTRADA, alternativaDaRespostaId));
			}
			alternativaDaRespostaRepository.deleteById(alternativaDaRespostaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_ALTERNATIVA_EM_USO, alternativaDaRespostaId));
		}
	}
	
	//No momento não está em uso
	public Optional<AlternativaDaResposta> atualizar ( Long alternativaDaRespostaId,  Categoria alternativaDaResposta ){
		Optional<AlternativaDaResposta> alternativaDaRespostaNoBancoDeDados = alternativaDaRespostaRepository.findById(alternativaDaRespostaId);
		if(alternativaDaRespostaNoBancoDeDados.isPresent()) {
			BeanUtils.copyProperties(alternativaDaResposta, alternativaDaRespostaNoBancoDeDados.get(), "id");
			alternativaDaRespostaRepository.save(alternativaDaRespostaNoBancoDeDados.get());
			return alternativaDaRespostaNoBancoDeDados;
		}
		return alternativaDaRespostaNoBancoDeDados;
	}	
	
	public AlternativaDaResposta buscarOuFalhar(Long alternativaDaRespostaId) {
		return buscaPorId(alternativaDaRespostaId).orElseThrow(() -> new AlternativaDaRespostaNãoEcontradaException(String.format(MSG_ALTERNATIVA_NAO_ENCONTRADA, alternativaDaRespostaId)));
	}
}
