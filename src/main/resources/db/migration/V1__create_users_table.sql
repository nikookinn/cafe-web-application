-- V1__create_users_table.sql
CREATE TABLE users (
                       username VARCHAR(50) NOT NULL PRIMARY KEY,
                       password VARCHAR(100) NOT NULL,
                       enabled BOOLEAN NOT NULL
);
