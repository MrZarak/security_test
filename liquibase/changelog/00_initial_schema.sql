CREATE TABLE IF NOT EXISTS users_security(
	id SERIAL PRIMARY KEY,
	login VARCHAR(32) UNIQUE NOT NULL,
	email VARCHAR(255) UNIQUE NOT NULL,
	password VARCHAR(255) NOT NULL,
	age INT NOT NULL DEFAULT 18,
	moneyAmount REAL NOT NULL DEFAULT 0.0,
	ROLE VARCHAR(16) NOT NULL DEFAULT 'Admin'
);

INSERT INTO users_security(login, email, password, age) VALUES( 'Zarak', 'mrzarak0303@gmail.com', 'aboba', 18);