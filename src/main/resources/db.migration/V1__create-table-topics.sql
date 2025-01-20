
create table topics(

    id bigint not null auto_increment,
    title varchar(50) not null,
    message varchar(100) not null,
    creation_date VARCHAR(20) not null,
    status tinyint not null,
    course varchar(50) not null,
    author varchar(50) not null,

    primary key(id)


);