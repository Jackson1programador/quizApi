create table categoria (
	id bigint not null auto_increment,
    nome varchar(60) not null,
    descricao varchar(600) not null,
    foto varchar(500) not null,
    data_cadastro datetime not null,
    data_atualizacao datetime not null,
    
    primary key(id)
)