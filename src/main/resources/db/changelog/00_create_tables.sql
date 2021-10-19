--liquibase formatted sql

--changeset zarak:create_tables
--comment: Создаёт таблицу users_security
CREATE TABLE IF NOT EXISTS users_security(
	id SERIAL PRIMARY KEY,
	login VARCHAR(32) UNIQUE NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	age INT NOT NULL DEFAULT 18,
	moneyAmount REAL NOT NULL DEFAULT 0.0,
	userRole VARCHAR(16) NOT NULL DEFAULT 'ADMIN'
);
--rollback DROP TABLE users_security;
