# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table con_abstract (
  id                        bigint not null,
  title                     varchar(255),
  cite                      varchar(255),
  link                      varchar(255),
  url                       varchar(255),
  publication_date          timestamp,
  abstract_body             varchar(255),
  constraint pk_con_abstract primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password_hash             varchar(255),
  constraint pk_user primary key (email))
;

create sequence con_abstract_seq;

create sequence user_seq;




# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists con_abstract;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists con_abstract_seq;

drop sequence if exists user_seq;

