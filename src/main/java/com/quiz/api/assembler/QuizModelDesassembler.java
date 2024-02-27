package com.quiz.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.quiz.api.model.input.QuizInputDTO;
import com.quiz.domain.model.Categoria;
import com.quiz.domain.model.Quiz;


@Component
public class QuizModelDesassembler {
	
	@Autowired
	ModelMapper modelMapper;
	
	public Quiz toDomainObject (QuizInputDTO quizInputDTO) {
		
		return modelMapper.map(quizInputDTO, Quiz.class);
		
	}

	public void copyToDomainObject(QuizInputDTO quizInputDTO, Quiz quiz) {
		// se tiver que referenciar uma entidade em outra precisamos estanciar a entidade referenciada.
		// exemplo quero referenciar dentro da categoria um quiz (sentido faria se fosse ao contrário, mas é so pra fins didáticos)
		// categoria.setQuiz(new Cozinha())
		// se não fizermos isso, o JPA, vai achar que queremos trocar o ID da quiz já referenciada por outro a ID
		// enquanto o que queremos fazer é trocar a referencia do quiz na categoria.
		quiz.setCategoria(new Categoria());
		
		
		modelMapper.map(quizInputDTO, quiz);
	}
}
