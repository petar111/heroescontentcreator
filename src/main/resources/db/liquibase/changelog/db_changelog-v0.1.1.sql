--liquibase formatted sql

--changeset petar:1
create table origin (
        date_created timestamp(6),
        date_last_updated timestamp(6),
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

--changeset petar:2
create table origin_version (
        date_created timestamp(6),
        id bigserial not null,
        origin_id bigint not null,
        version bigint not null unique,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
    );

alter table if exists origin_version 
   add constraint FK1_ORIGIN 
   foreign key (origin_id) 
   references origin;
   
 --changeset petar:3
 alter table origin_version drop column version;
 
 --changeset petar:4
 CREATE TABLE _user
(
    date_created timestamp(6),
    date_last_updated timestamp(6),
    id bigserial not null,
    username character varying(50) NOT NULL,
    backup_email character varying(100),
    email character varying(100) NOT NULL,
    password character varying(255) NOT NULL,
    CONSTRAINT _user_pkey PRIMARY KEY (id),
    CONSTRAINT _user_username_key UNIQUE (username)
)

--changeset petar:5
alter table _user add column account_status smallint not null;
alter table _user add constraint _account_statusfrom0to3 check (account_status between 0 and 3);

alter table _user add column credentials_expired boolean not null;

alter table _user add constraint _email_unique UNIQUE (email);
alter table _user add constraint _backup_email_unique UNIQUE (backup_email);



