CREATE TABLE IF NOT EXISTS account (
    id int not null auto_increment primary key,
    name varchar(50) not null,
    amount double not null
);