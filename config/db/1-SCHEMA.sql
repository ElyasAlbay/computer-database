drop schema if exists `computer-database-db`;
  create schema if not exists `computer-database-db`;
  use `computer-database-db`;

  drop table if exists computer;
  drop table if exists company;
  drop table if exists user;
  drop table if exists role;

  create table company (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    constraint pk_company primary key (id))
  ;

  create table computer (
    id                        bigint not null auto_increment,
    name                      varchar(255),
    introduced                timestamp NULL,
    discontinued              timestamp NULL,
    company_id                bigint default NULL,
    constraint pk_computer primary key (id))
  ;

  create table user (
    name                      varchar(255) not null,
    password                  varchar(255) not null,
    enabled		      boolean not null default true,
    constraint pk_user primary key (name))
  ;

  create table role (
    id			      bigint not null auto_increment,
    username		      varchar(255) not null,
    role		      varchar(255) not null,
    constraint pk_role primary key (id),
    unique key uni_username_role (role,username),
    key fk_username_idx (username))
  ;

  alter table computer add constraint fk_computer_company_1 foreign key (company_id) references company (id) on delete restrict on update restrict;
  alter table role add constraint fk_role_user_1 foreign key (username) references user (name) on delete restrict on update restrict;

  create index ix_computer_company_1 on computer (company_id);
  create index ix_computer_name on computer (name);
  create index ix_computer_introduced on computer (introduced);
  create index ix_computer_discontinued on computer (discontinued);
  create index ix_company_name on company (name);
