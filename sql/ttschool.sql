DROP DATABASE IF EXISTS ttschool;
CREATE DATABASE `ttschool`; 
USE `ttschool`;


CREATE TABLE school (
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
year INT(4) NOT NULL,
primary key(id),
CONSTRAINT uc_school UNIQUE (name, year),
KEY /*INDEX*/ name (name),
KEY /*INDEX*/ year (year)
) ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE `group` (
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL, 
room VARCHAR(50) NOT NULL, 
schoolid INT(11) NOT NULL,
primary key (id),
key name (name),
KEY /*INDEX*/ room (room),
FOREIGN KEY (schoolid) REFERENCES school (id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;

CREATE TABLE trainee (
id INT(11) NOT NULL AUTO_INCREMENT,
firstname VARCHAR(50) NOT NULL,
lastname VARCHAR(50) NOT NULL,
rating INT(2) NOT NULL,
groupid INT(11) DEFAULT NULL,
primary key (id),
KEY /*INDEX*/ firstname (firstname),
KEY /*INDEX*/ lastname (lastname),
KEY /*INDEX*/ rating (rating),
FOREIGN KEY (groupid) REFERENCES `group` (id) ON DELETE SET NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;
 
CREATE TABLE `subject` (
id INT(11) NOT NULL AUTO_INCREMENT,
name VARCHAR(50) NOT NULL,
primary key (id),
UNIQUE KEY /*INDEX*/ name (name) 
) ENGINE=INNODB DEFAULT CHARSET=utf8;

create table group_subject(
id INT(11) NOT NULL AUTO_INCREMENT,
groupid INT(11) NOT NULL,
subjectid INT(11) NOT NULL,
primary key (id),
FOREIGN KEY (groupid) REFERENCES `group` (id) ON DELETE CASCADE,
FOREIGN KEY (subjectid) REFERENCES `subject` (id) ON DELETE CASCADE
)ENGINE=INNODB DEFAULT CHARSET=utf8;






