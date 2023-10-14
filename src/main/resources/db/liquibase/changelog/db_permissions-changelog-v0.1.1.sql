--liquibase formatted sql

--changeset permissions_petar:1
create table resource (
        id bigint not null,
        description varchar(255),
        name varchar(255) not null unique,
        _type varchar(50) not null,
        primary key (id),
        constraint _type_constraint check (_type in ('SYSTEM', 'BUSINESS'))
);

create table access_type (
        id bigint not null,
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
        foreign key (resource_id) references resource (id),
        foreign key (access_type_id) references access_type (id)
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
        foreign key (role_id) references role (id),
        foreign key (authority_id) references authority (id)
);


CREATE OR REPLACE FUNCTION f_update_authority_on_resource_insert ()
RETURNS trigger AS
$$
     DECLARE
 		access_types record;
     BEGIN
        for access_types in select *
	       from access_type
	    loop 
			insert into authority(resource_id, access_type_id) values (NEW.id, access_type.id);
	    end loop;
	    
          RETURN NEW;
     END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER insert_authority_on_resource_insert
     AFTER INSERT ON resource
     FOR EACH ROW EXECUTE PROCEDURE f_update_authority_on_resource_insert();
     

CREATE OR REPLACE FUNCTION f_update_authority_on_access_type_insert ()
RETURNS trigger AS
$$
     DECLARE
 		resources record;
     BEGIN
        for resources in select *
	       from resource
	    loop 
			insert into authority(resource_id, access_type_id) values (resources.id, NEW.id);
	    end loop;
	    
          RETURN NEW;
     END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER insert_authority_on_access_type_insert
     AFTER INSERT ON access_type
     FOR EACH ROW EXECUTE PROCEDURE f_update_authority_on_access_type_insert();

insert into _role (name, description) values ('ADMIN', 'User with the higest priviledges in the system. Can manage every resource in the system.');
insert into _role (name, description) values ('SYSTEM_MODERATOR', 'User who can manage every system-related resource in the system. (Users, System parameters etc.)');
insert into _role (name, description) values ('CONTENT_MODERATOR', 'User who can manage every resource related to the concrete data. (Origin, Hero, Battle etc.)');
insert into _role (name, description) values ('CONTENT_CREATOR', 'User who can see and create only its own data related to the game.');


CREATE OR REPLACE FUNCTION f_insert_into_role_authority_after_authority_insert ()
RETURNS trigger AS
$$
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
        if authority_resource._type = 'SYSTEM' then
			insert into role_authority(role_id, authority_id) values (system_moderator.id, NEW.id);
		end if;
		if authority_resource._type = 'BUSINESS' then
			insert into role_authority(role_id, authority_id) values (content_moderator.id, NEW.id);
		end if;
		if authority_access_type.name = 'MANAGE_ONLY_OWN' then
			insert into role_authority(role_id, authority_id) values (content_creator.id, NEW.id);
		end if;
        
          RETURN NEW;
     END;
$$ LANGUAGE 'plpgsql';

CREATE TRIGGER insert_into_role_authority_after_authority_insert
     AFTER INSERT ON authority
     FOR EACH ROW EXECUTE PROCEDURE f_insert_into_role_authority_after_authority_insert();



insert into resource(id, name, description, _type) values (1, 'ORIGIN', '', 'BUSINESS');
insert into resource(id, name, description, _type) values (2, 'USER', '', 'SYSTEM');

insert into access_type(id, name, description) values (1, 'MANAGE_ONLY_OWN', 'User has permission on CRUD operations only on the resources that he created.');
insert into access_type(id, name, description) values (2, 'READ_ONLY', 'User has permission to retrieve all other resources.');
insert into access_type(id, name, description) values (3, 'MANAGE_ALL', 'User has permission on CRUD operations on all resources in the system.');
