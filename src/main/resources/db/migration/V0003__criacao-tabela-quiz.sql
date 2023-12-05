create table quiz (
	id bigint not null auto_increment,
    categoria_id bigint not null,
    dificuldade int not null,
    pergunta varchar(600) not null,
    alternativa_id_a bigint not null,
    alternativa_id_b bigint not null,
    alternativa_id_c bigint not null,
    alternativa_id_d bigint not null,
    alternativa_id_e bigint not null,
    resposta_certa_id bigint not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    
    primary key(id),
    FOREIGN KEY (categoria_id) REFERENCES categoria(id),
    FOREIGN KEY (alternativa_id_a) REFERENCES alternativa_da_resposta(id),
	FOREIGN KEY (alternativa_id_b) REFERENCES alternativa_da_resposta(id),
    FOREIGN KEY (alternativa_id_c) REFERENCES alternativa_da_resposta(id),
    FOREIGN KEY (alternativa_id_d) REFERENCES alternativa_da_resposta(id),
    FOREIGN KEY (alternativa_id_e) REFERENCES alternativa_da_resposta(id),
    FOREIGN KEY (resposta_certa_id) REFERENCES alternativa_da_resposta(id)

)