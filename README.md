# etapa1Vaga
Configurando ambiente:
-Netbeans IDE 8.2 (Completo)
-Java 8
-Glassfish 5
-db-derby-10.14.2.0-bin
-Node.js 10.14.0

1) Arquivos .jar derby copiados e colados em Glassfish/lib
2) Criar novo banco "estagio" e conectar 
   2.1. Criar novo servidor Glassfish e conectar
3. Criar as tabelas
create table VagaRequisito(
vaga integer references Vaga (idVaga),
requisito integer references Requisito (idRequisito),
CONSTRAINT primary_key_vr PRIMARY KEY (vaga, requisito)
);

create table Vaga
(
idVaga integer not null generated always as identity (start with 1, increment by 1),
title varchar(256) not null,
city varchar(256) not null,
state varchar(256) not null,
zipcode varchar(256) not null,
hider varchar(256),
description varchar(256),
salary float not null,
category integer references Categoria (idCategoria),
CONSTRAINT primary_key_vag PRIMARY KEY (idVaga)
);

create table Requisito
(
idRequisito integer not null generated always as identity (start with 1, increment by 1),
title varchar(256) not null,
CONSTRAINT primary_key_req PRIMARY KEY (idRequisito)
);

create table Categoria
(
idCategoria integer not null generated always as identity (start with 1, increment by 1),
title varchar(256) not null,
CONSTRAINT primary_key PRIMARY KEY (idCategoria)
);


4. Inserir dados nas tabelas
5. Mudar o nome da conexão do banco local, usuário e senha no arquivo VagaDAO.java
6. Testar WebServices RestFul
7. Executar projeto
8. http://localhost:8080/vagaEstagio/webresources/vaga/get -> pega uma vaga
9. http://localhost:8080/vagaEstagio/webresources/vaga/list -> lista todas as vagas

10.Pasta da front/vaga-frontend executar pelo terminal npm start.
