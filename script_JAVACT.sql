create database javact_db;

create table pessoa(
	id serial primary key,
	nome varchar(150) not null,
	cpf varchar(11) not null UNIQUE,
	dataNascimento date not null,
	contato varchar(20) not null,
	senha varchar(8) not null,
	tipo varchar(15) not null
);

create table funcionario(
	id int primary key,
	cargo varchar(100) not null,
	foreign key (id) references Pessoa(id)
);

create table personalTrainer(
	id int primary key,
	especialidade varchar(100) not null,
	cref varchar(50) not null,
	horarioAtendimento time,
	foreign key (id) references Pessoa(id)
);

create table plano(
	id serial primary key,
	nome varchar(100) not null,
	duracao varchar(50) not null,
	valor float not null,
	descricao varchar(255) not null
);

create table aluno (
	id int primary key,
	plano_id int not null,
	dataMatricula date not null,
	foreign key (id) references Pessoa(id),
	foreign key (plano_id) references Plano(id)
);

create table avaliacao(
	id serial primary key,
	aluno_id int,
	personal_id int,
	data date not null,
	descricao varchar(255) not null,
	foreign key (aluno_id) references aluno(id),
	foreign key (personal_id) references personalTrainer(id)
);

create table agendamento(
	id serial primary key,
	aluno_id int,
	personal_id int,
	data date not null,
	horario time not null,
	status varchar(20),
	foreign key (aluno_id) references aluno(id),
	foreign key (personal_id) references personalTrainer(id)
);