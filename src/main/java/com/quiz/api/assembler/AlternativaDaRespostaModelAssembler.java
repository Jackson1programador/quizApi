package com.quiz.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.AlternativaDaRespostaDTO;
import com.quiz.domain.model.AlternativaDaResposta;

@Component
public class AlternativaDaRespostaModelAssembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public AlternativaDaRespostaDTO toModel(AlternativaDaResposta alternativaDaResposta) {
		return modelMapper.map(alternativaDaResposta, AlternativaDaRespostaDTO.class);
	}
	
	public List<AlternativaDaRespostaDTO> toCollectionModel (List<AlternativaDaResposta> alternativasDasRespostas) {
		return alternativasDasRespostas.stream()
				.map(alternativaDaResposta -> toModel(alternativaDaResposta))
				.collect(Collectors.toList());
	}

}
