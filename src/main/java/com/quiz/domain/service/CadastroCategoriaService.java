package com.quiz.domain.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.quiz.domain.exception.CategoriaNãoEcontradaException;
import com.quiz.domain.exception.EntidadeEmUsoException;
import com.quiz.domain.model.Categoria;
import com.quiz.domain.repository.CategoriaRepository;

@Service
public class CadastroCategoriaService {

	private static final String MSG_CATEGORIA_EM_USO = "A categoria de código %d não pode ser removida, pois está em uso";
	private static final String MSG_CATEGORIA_NAO_ENCONTRADA = "Não existe o cadastro da categoria com esse código %d";
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public List<Categoria> list () {
		List<Categoria> list = categoriaRepository.findAll();
		return list;
	}
	
	
	public Optional<Categoria> buscaPorId (Long categoriaId) {
		Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
		return categoria;
	}
	
	
	public Categoria salvar (Categoria categoria) {
		return categoriaRepository.save(categoria);
	}
	
	
	public void excluir (Long categoriaId) {
		try {
			if (!categoriaRepository.existsById(categoriaId)) {
				throw new CategoriaNãoEcontradaException(
						String.format(MSG_CATEGORIA_NAO_ENCONTRADA, categoriaId));
			}
			categoriaRepository.deleteById(categoriaId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
			String.format(MSG_CATEGORIA_EM_USO, categoriaId));
		}
	}
	
	//No momento não está em uso
	public Optional<Categoria> atualizar ( Long categoriaId,  Categoria categoria ){
		Optional<Categoria> categoriaNoBancoDeDados = categoriaRepository.findById(categoriaId);
		if(categoriaNoBancoDeDados.isPresent()) {
			BeanUtils.copyProperties(categoria, categoriaNoBancoDeDados.get(), "id");
			categoriaRepository.save(categoriaNoBancoDeDados.get());
			return categoriaNoBancoDeDados;
		}
		return categoriaNoBancoDeDados;
	}	
	
	public Categoria buscarOuFalhar(Long categoriaId) {
		return buscaPorId(categoriaId).orElseThrow(() -> new CategoriaNãoEcontradaException(String.format(MSG_CATEGORIA_NAO_ENCONTRADA, categoriaId)));
	}
	
	
	
}
