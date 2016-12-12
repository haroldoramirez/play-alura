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

create table registro_de_acesso (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  uri                           varchar(255),
  data_de_acesso                datetime(6),
  constraint pk_registro_de_acesso primary key (id)
);

create table token_da_api (
  id                            bigint auto_increment not null,
  usuario_id                    bigint,
  codigo                        varchar(255),
  expicacao                     datetime(6),
  constraint uq_token_da_api_usuario_id unique (usuario_id),
  constraint pk_token_da_api primary key (id)
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
  papel                         varchar(7),
  constraint ck_usuario_papel check (papel in ('CLIENTE','ADMIN')),
  constraint pk_usuario primary key (id)
);

alter table registro_de_acesso add constraint fk_registro_de_acesso_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;
create index ix_registro_de_acesso_usuario_id on registro_de_acesso (usuario_id);

alter table token_da_api add constraint fk_token_da_api_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;

alter table token_de_cadastro add constraint fk_token_de_cadastro_usuario_id foreign key (usuario_id) references usuario (id) on delete restrict on update restrict;


# --- !Downs

alter table registro_de_acesso drop foreign key fk_registro_de_acesso_usuario_id;
drop index ix_registro_de_acesso_usuario_id on registro_de_acesso;

alter table token_da_api drop foreign key fk_token_da_api_usuario_id;

alter table token_de_cadastro drop foreign key fk_token_de_cadastro_usuario_id;

drop table if exists produto;

drop table if exists registro_de_acesso;

drop table if exists token_da_api;

drop table if exists token_de_cadastro;

drop table if exists usuario;

