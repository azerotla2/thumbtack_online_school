DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`; 
USE `ttschool`;

CREATE TABLE trainee (
id INT(11) NOT NULL AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
rating INT(2) NOT NULL,
primary key (id),
KEY /*INDEX*/ firstname (firstname),
KEY /*INDEX*/ lastname (lastname),
KEY /*INDEX*/ rating (rating)
) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
CREATE TABLE subjectStudent (
id INT(11) NOT NULL AUTO_INCREMENT,
nameSubject VARCHAR(50) NOT NULL,
primary key (id),
KEY /*INDEX*/ nameSubject (nameSubject)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE schools (
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
year INT(4) NOT NULL,
primary key(id),
KEY /*INDEX*/ name (name),
KEY /*INDEX*/ year (year)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

-- добавить новую таблицу и зависимсоти для Листа
CREATE TABLE groupStudent (
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL, 
room VARCHAR(50) NOT NULL, 
schoolid INT(11) NOT NULL,
primary key (id),
key name (name),
KEY /*INDEX*/ room (room),
FOREIGN KEY (schoolid) REFERENCES schools (id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;



