create table usuario (
	id bigint not null auto_increment,
	nome varchar(80) not null,
	sexo varchar(1),
	email varchar(255),
	data_nascimento datetime(0) not null,
	naturalidade varchar(100),
	nacionalidade varchar(100),
	cpf varchar(11) not null,
	
	primary key (id),
	unique key unique_usuario_cpf (cpf)
) engine=InnoDB default charset=utf8;
