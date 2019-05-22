drop database if exists sec_dev;
create database sec_dev;
use sec_dev;


-- =====================================================================================================================
-- Domain tables
-- =====================================================================================================================

create table account (
    id bigint unsigned not null auto_increment primary key,
    username varchar(50) not null,
    password varchar(50) not null,
    first_name varchar(50) not null,
    last_name varchar(50) not null,
    email varchar(50) not null,
    unique index account_idx1 (username)
) engine = InnoDb;

create table role (
    id bigint unsigned not null auto_increment primary key,
    name varchar(50) not null
) engine = InnoDb;

create table permission (
    id bigint unsigned not null auto_increment primary key,
    name varchar(50) not null
) engine = InnoDb;

create table account_role (
    id bigint unsigned not null auto_increment primary key,
    account_id bigint unsigned not null,
    role_id bigint unsigned not null,
    foreign key (account_id) references account (id),
    foreign key (role_id) references role (id),
    unique index account_role_idx1 (account_id, role_id)
) engine = InnoDb;

create table role_permission (
    id bigint unsigned not null auto_increment primary key,
    role_id bigint unsigned not null,
    permission_id bigint unsigned not null,
    foreign key (role_id) references role (id),
    foreign key (permission_id) references permission (id),
    unique index role_permission_idx1 (role_id, permission_id)
) engine = InnoDb;

create table possession (
    id bigint unsigned not null auto_increment primary key,
    name varchar(250) not null,
    owner_id bigint unsigned not null,
    foreign key (owner_id) references account (id),
    unique index possession_idx1 (name)
) engine = InnoDb;


-- ============================================================================
-- ACL tables
-- ============================================================================

create table acl_sid (
    id int unsigned not null auto_increment primary key,
    principal boolean not null,
    sid varchar(100) not null,
    unique index acl_sid_idx_1 (sid, principal)
) engine = InnoDb;

create table acl_class (
    id smallint unsigned not null auto_increment primary key,
    class varchar(100) unique not null
) engine = InnoDb;

create table acl_object_identity (
    id int unsigned not null auto_increment primary key,
    object_id_class smallint unsigned not null,
    object_id_identity int unsigned not null,
    parent_object int unsigned,
    owner_sid int unsigned,
    entries_inheriting boolean not null,
    unique index acl_object_identity_idx_1 (object_id_class, object_id_identity),
    foreign key (object_id_class) references acl_class (id),
    foreign key (parent_object) references acl_object_identity (id),
    foreign key (owner_sid) references acl_sid (id)
) engine = InnoDb;

create table acl_entry (
    id int unsigned not null auto_increment primary key,
    acl_object_identity int unsigned not null,
    ace_order int unsigned not null,
    sid int unsigned not null,
    mask int not null,
    granting boolean not null default 1,
    audit_success boolean not null default 0,
    audit_failure boolean not null default 0,
    foreign key (acl_object_identity) references acl_object_identity (id),
    foreign key (sid) references acl_sid (id)
) engine = InnoDb;


-- =====================================================================================================================
-- Procedures
-- =====================================================================================================================

delimiter //

create procedure createPermission($name varchar(50))
begin
    insert into permission (name) values ($name);
end //

create procedure createRole($name varchar(50), out $id smallint)
begin
    insert into role (name) values ($name);
    set $id := last_insert_id();
    
    -- Create the ACL SID for this role
    insert into acl_sid (principal, sid) values (0, $name);
end //

create procedure roleHasPermission($role_id smallint, $perm_name varchar(50))
begin
    declare _perm_id int;
    select id from permission where name = $perm_name into _perm_id;
    insert into role_permission (role_id, permission_id) values ($role_id, _perm_id);
end //

create procedure createAccount($name varchar(50), $first_name varchar(50), $last_name varchar(50), $email varchar(50), out $id int)
begin
    insert into account (username, password, first_name, last_name, email) values ($name, 'p@ssword', $first_name, $last_name, $email);
    set $id := last_insert_id();
    
    -- Create the ACL SID for this account
    insert into acl_sid (principal, sid) values (1, $name);
end //

create procedure accountHasRole($account_id bigint, $role_id smallint)
begin
    insert into account_role (account_id, role_id) values ($account_id, $role_id);
end //

create procedure createAclClass($name varchar(100), out $id smallint)
begin
    insert into acl_class (class) values ($name);
    set $id := last_insert_id();
end //

create procedure createPossession($name varchar(250), $owner_id int, out $id bigint)
begin
    declare _owner_sid int;
    declare _possession_oid smallint;
    
    insert into possession (name, owner_id) values ($name, $owner_id);
    set $id := last_insert_id();
    
    -- Now we need to create the ACL for this possession
    
    -- Create the OID
    select s.id from account a, acl_sid s where a.id = $owner_id and a.username = s.sid into _owner_sid;
    insert into acl_object_identity (object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) values
        (@possession_class, $id, null, _owner_sid, 1);
    set _possession_oid := last_insert_id();
    
    -- Give the owner read, write, create, delete and admin permissions by creating a possession ACL.
    -- Bitwise permission mask semantics: read (bit 0), write (bit 1), create (bit 2), delete (bit 3), admin (bit 4).
    insert into acl_entry (acl_object_identity, ace_order, sid, mask) values
        (_possession_oid, 0, _owner_sid, 1), -- read
        (_possession_oid, 1, _owner_sid, 2), -- write
        (_possession_oid, 2, _owner_sid, 4), -- create
        (_possession_oid, 3, _owner_sid, 8), -- delete
        (_possession_oid, 4, _owner_sid, 16); -- admin
end //
