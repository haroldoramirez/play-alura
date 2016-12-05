# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table produto (
  id                            bigint auto_increment not null,
  titulo                        varchar(255),
  codigo                        varchar(255),
  tipo                          varchar(255),
  descricao                     varchar(255),
  preco                         double,
  constraint pk_produto primary key (id)
);

create table token_de_cadastro (
  id                            bigint auto_increment not null,
  codigo                        varchar(255),
  usuario_id                    bigint,
  constraint uq_token_de_cadastro_usuario_id unique (usuario_id),
  constraint pk_token_de_cadastro primary key (id)
);

create table usuario (
  id                            bigint auto_increment not null,
  nome                          varchar(255),
  email                         varchar(255),
  senha                         varchar(255),
  verificado                    tinyint(1) default 0,
  constraint pk_usuario primary key (id)
);

alter table token_de_cadastro add constraint fk_token_de_cadastro_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;


# --- !Downs

alter table token_de_cadastro drop foreign key fk_token_de_cadastro_usuario_id;

drop table if exists produto;

drop table if exists token_de_cadastro;

drop table if exists usuario;

