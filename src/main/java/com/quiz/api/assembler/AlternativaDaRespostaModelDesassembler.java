package com.quiz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.AlternativaDaRespostaInputDTO;
import com.quiz.domain.model.AlternativaDaResposta;

@Component
public class AlternativaDaRespostaModelDesassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public AlternativaDaResposta toDomainObject (AlternativaDaRespostaInputDTO alternativaDaRespostaInputDTO) {
		return modelMapper.map(alternativaDaRespostaInputDTO, AlternativaDaResposta.class);
	}

	public void copyToDomainObject(AlternativaDaRespostaInputDTO alternativaDaRespostaInputDTO, AlternativaDaResposta alternativaDaResposta) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		
		
		modelMapper.map(alternativaDaRespostaInputDTO, alternativaDaResposta);
	}
}
