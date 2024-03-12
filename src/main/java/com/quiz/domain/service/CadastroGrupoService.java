package com.quiz.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.exception.GrupoNaoEncontradoExecption;
import com.quiz.domain.model.Grupo;
import com.quiz.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {

	private static final String MSG_GRUPO_EM_USO = "O grupo de código %d não pode ser removida, pois está em uso";
	private static final String MSG_GRUPO_NAO_ENCONTRADA = "Não existe o cadastro do grupo com esse código %d";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	public List<Grupo> list () {
		List<Grupo> list = grupoRepository.findAll();
		return list;
	}
	
	
	public Optional<Grupo> buscaPorId (Long grupoId) {
		Optional<Grupo> grupo = grupoRepository.findById(grupoId);
		return grupo;
	}
	
	
	@Transactional
	public Grupo salvar (Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	
	@Transactional
	public void excluir (Long grupoId) {
		try {
			if (!grupoRepository.existsById(grupoId)) {
				throw new GrupoNaoEncontradoExecption(
						String.format(MSG_GRUPO_NAO_ENCONTRADA, grupoId));
			}
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}
	
	
	public Grupo buscarOuFalhar(Long grupoId) {
		return buscaPorId(grupoId).orElseThrow(() -> new GrupoNaoEncontradoExecption(String.format(MSG_GRUPO_NAO_ENCONTRADA, grupoId)));
	}
	
}
