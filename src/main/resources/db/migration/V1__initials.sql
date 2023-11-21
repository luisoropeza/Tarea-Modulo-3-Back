CREATE TABLE rol (
    id serial PRIMARY KEY,
    name varchar(100) NOT NULL
);

CREATE TABLE users (
    id serial PRIMARY KEY,
    created_at timestamp NULL,
    email varchar(150) NOT NULL,
    password varchar(150) NOT NULL,
    username varchar(150) NOT NULL
);

CREATE TABLE user_detail (
    id serial PRIMARY KEY,
    age int NULL,
    birth_day date NULL,
    first_name varchar(100) NOT NULL,
    last_name varchar(100) NOT NULL,
    user_id int REFERENCES users(id) UNIQUE
);

CREATE TABLE user_rol (
    id serial PRIMARY KEY,
    active bool NOT NULL,
    created_at timestamp NOT NULL,
    rol_id int REFERENCES rol(id),
    user_id int REFERENCES users(id)
);