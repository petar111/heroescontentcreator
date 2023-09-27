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
