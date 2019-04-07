CREATE DATABASE IF NOT EXISTS `strefakursow`;

USE `strefakursow`;



DROP TABLE IF EXISTS `employee`;



CREATE TABLE `employee` (
	
	`id_employee` int not null auto_increment,
    
	`first_name` varchar(40) default null,
    
	`last_name` varchar(40) default null,
    
	`salary` int default 0,
    
	primary key (`id_employee`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET= utf8mb4;