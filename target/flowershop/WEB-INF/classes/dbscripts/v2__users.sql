create table Users -- default values are specified in source code
(
	login varchar(20) not null,
	name varchar(20) not null,
    isAdmin boolean not null,
    password varchar(20) not null,
    balance decimal(10,2) not null,
    discount int not null,
	constraint PK_Users primary key(login)
);