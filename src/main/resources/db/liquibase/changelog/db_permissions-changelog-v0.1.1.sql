--liquibase formatted sql

--changeset permissions_petar:1
create table resource (
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        _type smallint not null,
        primary key (id),
        constraint _type_constraint check (_type between 0 and 1)
);

create table access_type (
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);
COMMENT ON TABLE access_type IS 'Access type determines which the permission whthin the scope and the type of operation on the resource.';

create table authority (
		id bigserial not null,
        resource_id bigint not null,
        access_type_id bigint not null,
        primary key (id),
        foreign key (resource_id) references resource (id) on delete cascade,
        foreign key (access_type_id) references access_type (id) on delete cascade
);

create table _role (
        id bigserial not null,
        description varchar(255),
        name varchar(255) not null unique,
        primary key (id)
);

create table role_authority(
		id bigserial not null,
        role_id bigint not null,
        authority_id bigint not null,
        primary key (id),
        foreign key (role_id) references _role (id) on delete cascade,
        foreign key (authority_id) references authority (id) on delete cascade
);

--changeset permissions_petar:1.1 splitStatements:false
CREATE OR REPLACE FUNCTION f_update_authority_on_resource_insert ()
RETURNS trigger AS
$BODY$
     DECLARE
 		access_types record;
     BEGIN
        for access_types in select * from access_type
	    loop 
			insert into authority(resource_id, access_type_id) values (NEW.id, access_types.id);
	    end loop;
	    
          RETURN NEW;
     END;
$BODY$ LANGUAGE 'plpgsql';

--changeset permissions_petar:1.1.1
CREATE TRIGGER insert_authority_on_resource_insert
     AFTER INSERT ON resource
     FOR EACH ROW EXECUTE PROCEDURE f_update_authority_on_resource_insert();
     
--changeset permissions_petar:1.2 splitStatements:false
CREATE OR REPLACE FUNCTION f_update_authority_on_access_type_insert ()
RETURNS trigger AS
$BODY$
     DECLARE
 		resources record;
     BEGIN
        for resources in select * from resource
	    loop 
			insert into authority(resource_id, access_type_id) values (resources.id, NEW.id);
	    end loop;
	    
          RETURN NEW;
     END;
$BODY$ LANGUAGE 'plpgsql';

--changeset permissions_petar:1.2.1
CREATE TRIGGER insert_authority_on_access_type_insert
     AFTER INSERT ON access_type
     FOR EACH ROW EXECUTE PROCEDURE f_update_authority_on_access_type_insert();

--changeset permissions_petar:1.3 splitStatements:false
CREATE OR REPLACE FUNCTION f_insert_into_role_authority_after_authority_insert ()
RETURNS trigger AS
$BODY$
     DECLARE
 		authority_resource record;
 		authority_access_type record;
 		_admin record;
 		system_moderator record;
 		content_moderator record;
 		content_creator record;
     BEGIN
        select * into _admin from _role where name = 'ADMIN';
        select * into system_moderator from _role where name = 'SYSTEM_MODERATOR';
        select * into content_moderator from _role where name = 'CONTENT_MODERATOR';
        select * into content_creator from _role where name = 'CONTENT_CREATOR';
	    
        select * into authority_resource from resource where id = NEW.resource_id;
        select * into authority_access_type from access_type where id = NEW.access_type_id;
        
        insert into role_authority(role_id, authority_id) values (_admin.id, NEW.id);
        if authority_resource._type = 0 then
			insert into role_authority(role_id, authority_id) values (system_moderator.id, NEW.id);
		end if;
		if authority_resource._type = 1 then
			insert into role_authority(role_id, authority_id) values (content_moderator.id, NEW.id);
		end if;
		if authority_access_type.name = 'MANAGE_ONLY_OWN' then
			insert into role_authority(role_id, authority_id) values (content_creator.id, NEW.id);
		end if;
        
          RETURN NEW;
     END;
$BODY$ LANGUAGE 'plpgsql';

--changeset permissions_petar:1.3.1
CREATE TRIGGER insert_into_role_authority_after_authority_insert
     AFTER INSERT ON authority
     FOR EACH ROW EXECUTE PROCEDURE f_insert_into_role_authority_after_authority_insert();

--changeset permissions_petar:1.4
alter table _user add column role_id bigint not null;
alter table _user add constraint user_role_fk foreign key (role_id) references _role (id);
     
insert into _role (name, description) values ('ADMIN', 'User with the higest priviledges in the system. Can manage every resource in the system.');
insert into _role (name, description) values ('SYSTEM_MODERATOR', 'User who can manage every system-related resource in the system. (Users, System parameters etc.)');
insert into _role (name, description) values ('CONTENT_MODERATOR', 'User who can manage every resource related to the concrete data. (Origin, Hero, Battle etc.)');
insert into _role (name, description) values ('CONTENT_CREATOR', 'User who can see and create only its own data related to the game.');

insert into resource(name, description, _type) values ('ORIGIN', '', 1);
insert into resource(name, description, _type) values ('USER', '', 0);
insert into resource(name, description, _type) values ('DOCUMENTATION', '', 0);

insert into access_type(name, description) values ('MANAGE_ONLY_OWN', 'User has permission on CRUD operations only on the resources that he created.');
insert into access_type(name, description) values ('READ_ONLY', 'User has permission to retrieve all other resources.');
insert into access_type(name, description) values ('MANAGE_ALL', 'User has permission on CRUD operations on all resources in the system.');

--changeset permissions_petar:1.5
insert into _user (username, password, email, backup_email, account_status, credentials_expired, role_id, date_created)
			values('admin', '{bcrypt}$2a$10$qFMfYsTSvrYxmPtRb8D08urf2s8QTrTau2UjD1F9fu02Phr6qJ2Le', 'admin132312123123@gmail.com', 'admin432423r342@gmail.com', 0, false, (select id from _role where name='ADMIN'),'1990-10-16 17:00:12.659');


