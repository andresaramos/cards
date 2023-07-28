drop table if exists CARDS;
drop table if exists OPERATIONS;

create table CARDS
(
    id    bigint(20) not null auto_increment,
    marca varchar(20) not null,
    numero varchar(20) not null,
    holder varchar(100) not null,
    fecha_vencimiento  timestamp not null,
    primary key (id)
);

create table OPERATIONS
(
    id bigint(20) not null auto_increment,
    importe integer,
    card_id bigint(20) references CARDS(id) on delete cascade,
    primary key (id)
);