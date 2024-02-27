package com.quiz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.CategoriaInputDTO;
import com.quiz.domain.model.Categoria;

@Component
public class CategoriaModelDesassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Categoria toDomainObject (CategoriaInputDTO categoriaInputDTO) {
		//Categoria categoria = new Categoria();
		//categoria.setNome(categoriaInputDTO.getNome());
		//categoria.setDescricao(categoriaInputDTO.getDescricao());
		//categoria.setFoto(categoriaInputDTO.getFoto());
		//return categoria;
		
		return modelMapper.map(categoriaInputDTO, Categoria.class);
		
	}

	public void copyToDomainObject(CategoriaInputDTO categoriaInputDTO, Categoria categoria) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		
		
		modelMapper.map(categoriaInputDTO, categoria);
	}
}
