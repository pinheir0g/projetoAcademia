# JavaCT

## Sistema de Gerenciamento de Academia

Este projeto é um sistema de gerenciamento de academia desenvolvido como proposta final para a disciplina de Programação Orientada a Objetos do SERRATEC. O objetivo é simular o funcionamento de uma academia, permitindo o cadastro de alunos, personal trainers, planos e a gestão de agendamentos.

## Tecnologias:
- Java
- JDBC
- PostgreSQL
- DBeaver

## Requisitos:
Para conseguir executar o projeto é necessário ter instalado Java, PostgreSQL e o driver do JDBC para conectar com o PostgreSQL.


Selecionar a versão mais recente do PostgreSQL e do Driver JDBC e do Java selecionar a versão JDK 21.

### Downloads:
- [Java](https://www.oracle.com/br/java/technologies/downloads/#jdk21-windows)
- [PostgreSQL](https://www.enterprisedb.com/downloads/postgres-postgresql-downloads)
- [Driver JDBC](https://jdbc.postgresql.org/download/)


## Funcionamento:

O sistema é executado como um menu interativo no console. O usuário faz login informando seu CPF e senha. Dependendo do tipo de usuário (aluno, personal trainer ou funcionário), um menu específico é fornecido com opções relacionadas às suas funcionalidades.

### Aluno:

- Visualizar dados pessoais e plano contratado.
- Solicitar agendamento de horário com personal trainer.
- Visualizar histórico de agendamentos.
- Cancelar agendamento.
- Visualizar avaliações físicas.

### Personal Trainer:

- Visualizar agenda de atendimentos.
- Registrar avaliações físicas dos alunos.
- Visualizar lista de avaliações realizadas.
### Funcionário:

- Cadastrar novo plano.
- Cadastrar novo aluno.
- Cadastrar novo Personal Trainer.
- Emitir relatório de planos.
- Emitir relatório de alunos.
- Emitir relatório de equipe (funcionários e personal trainers).
- Emitir relação de avaliações físicas por período.


## Executando
Antes de executar o projeto é necessário fazer a criação do banco de dados e das tabelas, para isso execute o [script SQL](script_JAVACT.sql) dentro do PgAdmin que veio ao baixar o PostgreSQL ou em algum SGBD de sua preferência.

Depois dentro da Classe Conexao.java alterar as variáveis `usuário` e `senha` e por os valores que você cadastrou quando instalou o PostgreSQL.

Após ter setado as variáveis corretamente e executado o script com sucesso, basta abrir o projeto em alguma IDE e executar o programa ou navegar até a pasta onde contém o arquivo Main.java e executar no terminal através do comando: 
```Bash
javac Main.java

java Main
```

### Desenvolvedores:
Gustavo Pinheiro -> [GitHub](https://github.com/pinheir0g)

João Marcelo -> [GitHub](https://github.com/JoaoMOA2024)

Léo Esplinio -> [GitHub](https://github.com/LeoEsplinio)

Matheus Augusto -> [GitHub](https://github.com/matheusdalbone)

Bruno Jacomelli -> [GitHub](https://github.com/brunojacomelli)

// Em desenvolvimento