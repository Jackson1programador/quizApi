package com.quiz.api.assembler;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.GrupoInputDTO;
import com.quiz.domain.model.Grupo;

@Component
public class GrupoModelDesassembler {

	@Autowired
	ModelMapper modelMapper;
	
	public Grupo toDomainObject (GrupoInputDTO grupoInputDTO) {
		
		return modelMapper.map(grupoInputDTO, Grupo.class);
		
	}

	public void copyToDomainObject(GrupoInputDTO grupoInputDTO, Grupo grupo) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		
		grupo.setPermissoes(new ArrayList<>());		
		modelMapper.map(grupoInputDTO, grupo);
	}
}
