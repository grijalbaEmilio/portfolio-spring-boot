-- CREATE DATABASE IF NOT EXISTS grijlaba_emili_portfolio;
USE grijalba_emilio_database;

-- ------------------------------------------------
-- CREATE TABLES 
-- ------------------------------------------------

CREATE TABLE IF NOT EXISTS project_tbl(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    title VARCHAR(45) NOT NULL,
	description VARCHAR(300) NOT NULL,
    demo_url VARCHAR(100),
    code_url VARCHAR(100),
    img_url VARCHAR(100) UNIQUE NOT NULL,
    technologies varchar(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS user_tbl(
	id INT NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(45) NOT NULL,
    last_name VARCHAR(45) NOT NULL,
    email VARCHAR(45) NOT NULL UNIQUE,
    password VARCHAR(100) NOT NULL,
    role ENUM('ADMIN', 'REVIEWER') DEFAULT 'REVIEWER'
);

CREATE TABLE IF NOT EXISTS comment_tbl(
	id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(300) NOT NULL,
    date_post DATE NOT NULL,
    likes INT DEFAULT 0,
    parent_id INT,
    author_id INT NOT NULL
);

DROP TABLE comment_tbl;

-- ------------------------------------------------
-- DECLARACIÓN DE CLAVES PRIMARIAS
-- ------------------------------------------------

ALTER TABLE user_tbl
ADD CONSTRAINT pk_user PRIMARY KEY(id);

ALTER TABLE project_tbl
ADD CONSTRAINT pk_project PRIMARY KEY(id); 

ALTER TABLE comment_tbl
ADD CONSTRAINT pk_comment PRIMARY KEY(id); 

-- ------------------------------------------------
-- CREATE TABLES RELATIONS
-- ------------------------------------------------

ALTER TABLE comment_tbl 
ADD CONSTRAINT fk_comment_author FOREIGN KEY(author_id) 
REFERENCES user_tbl(id) ON UPDATE CASCADE ON DELETE CASCADE;

ALTER TABLE comment_tbl 
ADD CONSTRAINT fk_comment_parent FOREIGN KEY(parent_id) 
REFERENCES comment_tbl(id) ON UPDATE CASCADE ON DELETE CASCADE;

-- ------------------------------------------------
-- CREATE PROJECTS
-- ------------------------------------------------

INSERT INTO project_tbl(title, description, demo_url, code_url, img_url, technologies)
VALUES
("portfolio", "es un pryectico pequeño", null, null, "no img", "mongo, java, hibernate, react");

select * from project_tbl;

DELETE FROM project_tbl
WHERE id > 8;
