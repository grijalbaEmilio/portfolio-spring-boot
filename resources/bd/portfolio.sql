# CREATE DATABASE IF NOT EXISTS grijalbaEmilio_portfolio_bd;

USE grijlabaEmilio_portfolio_bd;

CREATE TABLE user_tbl(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    name VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    age INT NOT NULL
);

CREATE TABLE comment_tbl(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE
);

CREATE TABLE project_tbl(
	id INT NOT NULL PRIMARY KEY AUTO_INCREMENT UNIQUE,
    title VARCHAR(45) NOT NULL,
    description VARCHAR(150) NOT NULL,
    demoUrl VARCHAR(100),
    codeUrl VARCHAR(100)
);