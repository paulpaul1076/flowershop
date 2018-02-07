--use flowershop;

create table Users
(
    id bigint not null identity(0, 1),
    isAdmin boolean not null default false,
    login varchar(20) not null,
    password varchar(20) not null,
    balance decimal(10,2) not null default 2000,
    discount int not null,
    name varchar(20) not null
);