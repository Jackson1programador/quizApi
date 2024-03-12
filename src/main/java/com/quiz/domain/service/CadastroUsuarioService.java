package com.quiz.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.exception.UsuarioNaoEncontradoException;
import com.quiz.domain.model.Usuario;
import com.quiz.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {

	private static final String MSG_USUARIO_EM_USO = "O usuario de código %d não pode ser removida, pois está em uso";
	private static final String MSG_USUARIO_NAO_ENCONTRADA = "Não existe o cadastro do usuario com esse código %d";
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public List<Usuario> list () {
		List<Usuario> list = usuarioRepository.findAll();
		return list;
	}
	
	
	public Optional<Usuario> buscaPorId (Long usuarioId) {
		Optional<Usuario> usuario = usuarioRepository.findById(usuarioId);
		return usuario;
	}
	
	
	@Transactional
	public Usuario salvar (Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	
	@Transactional
	public void excluir (Long usuarioId) {
		try {
			if (!usuarioRepository.existsById(usuarioId)) {
				throw new UsuarioNaoEncontradoException(
						String.format(MSG_USUARIO_NAO_ENCONTRADA, usuarioId));
			}
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
	}
	
	
	public Usuario buscarOuFalhar(Long usuarioId) {
		return buscaPorId(usuarioId).orElseThrow(() -> new UsuarioNaoEncontradoException(String.format(MSG_USUARIO_NAO_ENCONTRADA, usuarioId)));
	}
	
}
