# --- Created by Ebean DDL
# To stop Ebean DDL generation, remove this comment and start using Evolutions

# --- !Ups

create table question (
  id                        bigint not null,
  full_question             varchar(255),
  difficulty_level          integer,
  avg_time                  integer,
  type                      integer,
  owner_email               varchar(255),
  constraint pk_question primary key (id))
;

create table user (
  email                     varchar(255) not null,
  name                      varchar(255),
  password_hash             varchar(255),
  constraint pk_user primary key (email))
;

create sequence question_seq;

create sequence user_seq;

alter table question add constraint fk_question_owner_1 foreign key (owner_email) references user (email) on delete restrict on update restrict;
create index ix_question_owner_1 on question (owner_email);



# --- !Downs

SET REFERENTIAL_INTEGRITY FALSE;

drop table if exists question;

drop table if exists user;

SET REFERENTIAL_INTEGRITY TRUE;

drop sequence if exists question_seq;

drop sequence if exists user_seq;

