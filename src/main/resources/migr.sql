-- this part is for jdbc authentication
create table users
(
    username varchar(255) not null
        primary key,
    password varchar(255) not null,
    enabled  boolean      not null
);

create table authorities
(
    username  varchar(255) not null
        references users,
    authority varchar(255) not null,
    unique (username, authority)
);

insert into users values('admin','{bcrypt}$2a$12$EE/ZAAWZA3k71/n0DNoDXOzyAcOpSB4lobZaUR3CjPI3mkQi77OkS',true);
insert into users values('user','{bcrypt}$2a$12$EE/ZAAWZA3k71/n0DNoDXOzyAcOpSB4lobZaUR3CjPI3mkQi77OkS',true);

insert into authorities values ('admin','ROLE_ADMIN');
insert into authorities values ('admin','ROLE_USER');
insert into authorities values ('user','ROLE_USER');

-- this part for DAO authentication
-- show usernames and roles
select username,r.name  from  users
                                  join users_roles ur on users.id = ur.user_id
                                  join roles r on r.id = ur.role_id;

