DROP TABLE Person;

CREATE TABLE Person(
    id int PRIMARY KEY GENERATED BY DEFAULT AS IDENTITY,
    username varchar(100) NOT NULL,
    year_of_birth int NOT NULL,
    password varchar NOT NULL,
    role varchar(100) NOT NULL
);

insert INTO Person (username, year_of_birth, password, role) VALUES ('admin', 1960, '$2a$10$FKWNuZNDQbGwGyYJgEgf/elX9XuTxgRvPRf42aJFaAp4wbbD6Etny', 'ROLE_ADMIN');
