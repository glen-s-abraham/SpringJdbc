CREATE TABLE IF NOT EXISTS course (
	id integer PRIMARY KEY AUTO_INCREMENT NOT NULL,
	title varchar(100) NOT NULL,
	description varchar(300) NOT NULL,
	link varchar(50) NOT NULL
);