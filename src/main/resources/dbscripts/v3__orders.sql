create table orders
(
    orderid int not null,
    custlogin varchar(20) not null,
    total decimal(10,2) not null,
    createdate date not null,
    closedate date,
    status varchar(10) not null check (status in ('created', 'paid', 'closed')),
    foreign key (custlogin) references Users(login),
    constraint PK_Orders primary key(orderid )
);