CREATE SCHEMA Estudo;


CREATE TABLE Tarefa (
	id BIGINT NOT NULL AUTO_INCREMENT,
	descricao VARCHAR(255),
	finalizado BOOLEAN,
	dataFinalizacao DATE,
	primary key (id)
);

CREATE TABLE Usuario (
	login VARCHAR(255),
	senha VARCHAR(255)
);