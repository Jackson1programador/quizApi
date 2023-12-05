create table alternativa_da_resposta (
	id bigint not null auto_increment,
    conteudo varchar(600) not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    
    primary key(id)
)