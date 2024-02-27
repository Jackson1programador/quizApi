package com.quiz;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.TestPropertySource;

import com.quiz.domain.model.Categoria;
import com.quiz.domain.service.CadastroCategoriaService;
import com.quiz.util.DatabaseCleaner;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;


@SpringBootTest
//@TestPropertySource("/application-test.properties")
@TestPropertySource (locations = "classpath:application-test.properties")
public class CadastroCategoria {
	
	@Autowired
	private DatabaseCleaner databaseCleaner;
	
	@Autowired
	private CadastroCategoriaService cadastroCategoria;
	
	
	@BeforeEach
	public void setUp() {
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port = 8080;
		RestAssured.basePath = "/categoria";
		databaseCleaner.clearTables();
		prepararDados();
	}

	@Test
	public void deveRetornarStatus200_quandoConsultarCategoria() {
		RestAssured
		.given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus201_quandoCriarCategoria() {		
		RestAssured
		.given()
			.body("{\"nome\": \"medicina\",\"descricao\":\"pergunta de medicina\",\"foto\":\"teste\"}")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void deveRetornarStatus200_quandoConsultarCategoriaExistente() {
		RestAssured
		.given()
			.pathParam("categoriaId", 1)
			.accept(ContentType.JSON)
		.when()
			.get("/{categoriaId}")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void deveRetornarStatus404_quandoConsultarCategoriaInxistente() {
		RestAssured
		.given()
			.pathParam("categoriaId", 5000)
			.accept(ContentType.JSON)
		.when()
			.get("/{categoriaId}")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
	private void prepararDados() {
		Categoria categoria1 = new Categoria();
		categoria1.setNome("medicina");
		categoria1.setDescricao("teste");
		categoria1.setFoto("descrição");
		cadastroCategoria.salvar(categoria1);
		
		Categoria categoria2 = new Categoria();
		categoria2.setNome("contabilidade");
		categoria2.setDescricao("teste");
		categoria2.setFoto("descrição");
		cadastroCategoria.salvar(categoria2);
	}
	
	
	
}
