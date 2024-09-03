CREATE TABLE users (
    id varchar(255),
    username varchar(255),
    firstName varchar(255),
    lastName varchar(255)
);

CREATE TABLE transactions (
    id varchar(255),
    type varchar(255),
    amount integer;
);

CREATE TABLE products (
    id varchar(255),
    type varchar(255),
    name varchar(255);
);