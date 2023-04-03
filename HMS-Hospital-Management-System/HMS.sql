DROP DATABASE IF EXISTS hospital;
CREATE DATABASE hospital;
USE hospital;

--
-- Table structure for table `Users`
--

DROP TABLE IF EXISTS `Admins`;
CREATE TABLE `Admins` (
  `id` INT(11) NOT NULL auto_increment,
  `firstname` VARCHAR(50) NOT NULL default '',
  `lastname` VARCHAR(50) NOT NULL default '',
   `username` VARCHAR(50) NOT NULL default '',
  `password` VARCHAR(50) NOT NULL default '',
  `email` VARCHAR(50) NOT NULL default '',
  `position` VARCHAR(50) NOT NULL default '',
  `telephone` VARCHAR(50) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

LOCK TABLES `Admins` WRITE;
INSERT INTO `Admins` VALUES ("0","Teric","Simons","Janedoe","Janedoe123","tericsimons12@gmail.com","Receptionist","876-891-0011");
UNLOCK TABLES;

DROP TABLE IF EXISTS `Patients`;
CREATE TABLE `Patients` (
  `id` INT(11) NOT NULL auto_increment,
  `name` VARCHAR(50) NOT NULL default '',
  `occupation` VARCHAR(50) NOT NULL default '',
  `email` VARCHAR(50) NOT NULL default '',
  `gender` VARCHAR(50) NOT NULL default '',
  `telephone` VARCHAR(50) NOT NULL default '',
  `symptoms` VARCHAR(100) NOT NULL default '',
  `blood` VARCHAR(50) NOT NULL default '',
  `address` VARCHAR(100) NOT NULL default '',
  `doctor_seen` VARCHAR(50) NOT NULL default '',
  `amount_paid` VARCHAR(50) NOT NULL default '',
  `app_date` VARCHAR(50) NOT NULL default '',
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;

LOCK TABLES `Patients` WRITE;
INSERT INTO `Patients` VALUES ("0","Teric Simons","Student","tericsimons12@gmail.com","Male","876-891-0011","Cancer", "AB","Jew","Grant","5000","12/11/3");
UNLOCK TABLES;

DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback` (
  `name` VARCHAR(50) NOT NULL default '',
  `email` VARCHAR(50) NOT NULL default '',
  `feedback` VARCHAR(100) NOT NULL default ''
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4;