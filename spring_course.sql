-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 26-Out-2018 às 14:10
-- Versão do servidor: 5.7.21
-- PHP Version: 7.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `spring_course`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `course`
--

DROP TABLE IF EXISTS `course`;
CREATE TABLE IF NOT EXISTS `course` (
  `courseid` bigint(20) NOT NULL,
  `coursename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `course`
--

INSERT INTO `course` (`courseid`, `coursename`) VALUES
(5, 'Marketing 1'),
(6, 'Marketing 2'),
(7, 'Programming Java'),
(8, 'Spring Boot basics'),
(14, 'Marketing 1'),
(15, 'Marketing 2'),
(16, 'Programming Java'),
(17, 'Spring Boot basics'),
(23, 'Marketing 1'),
(24, 'Marketing 2'),
(25, 'Programming Java'),
(26, 'Spring Boot basics');

-- --------------------------------------------------------

--
-- Estrutura da tabela `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE IF NOT EXISTS `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `hibernate_sequence`
--

INSERT INTO `hibernate_sequence` (`next_val`) VALUES
(30),
(30);

-- --------------------------------------------------------

--
-- Estrutura da tabela `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `student`
--

INSERT INTO `student` (`id`, `department`, `email`, `firstname`, `lastname`) VALUES
(2, 'IT', 'mary@robinson.com', 'Mary', 'Robinson'),
(28, 'testeeeee', 'teste@teste.com', 'teste', 'teste'),
(4, 'Business', 'diana@doll.com', 'Diana', 'Doll'),
(9, 'IT', 'john@john.com', 'John', 'Johnson'),
(10, 'IT', 'steve.stevent@st.com', 'Steve', 'Stevens'),
(11, 'IT', 'mary@robinson.com', 'Mary', 'Robinson'),
(12, 'Nursery', 'kate@kate.com', 'Kate', 'Keystone'),
(13, 'Business', 'diana@doll.com', 'Diana', 'Doll'),
(18, 'IT', 'john@john.com', 'John', 'Johnson'),
(19, 'IT', 'steve.stevent@st.com', 'Steve', 'Stevens'),
(20, 'IT', 'mary@robinson.com', 'Mary', 'Robinson'),
(21, 'Nursery', 'kate@kate.com', 'Kate', 'Keystone'),
(29, 'testeee', 'teste2@teste.com', 'teste2', 'teste2');

-- --------------------------------------------------------

--
-- Estrutura da tabela `student_course`
--

DROP TABLE IF EXISTS `student_course`;
CREATE TABLE IF NOT EXISTS `student_course` (
  `id` bigint(20) NOT NULL,
  `courseid` bigint(20) NOT NULL,
  PRIMARY KEY (`id`,`courseid`),
  KEY `FKl348l2fhyxr5msuv4jwjr2w7f` (`courseid`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `student_course`
--

INSERT INTO `student_course` (`id`, `courseid`) VALUES
(9, 7),
(9, 8),
(18, 16),
(18, 17);

-- --------------------------------------------------------

--
-- Estrutura da tabela `user`
--

DROP TABLE IF EXISTS `user`;
CREATE TABLE IF NOT EXISTS `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `password`, `role`, `username`) VALUES
(1, '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER', 'user'),
(2, '$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2', 'ADMIN', 'admin'),
(3, '$2a$10$zwL0GU5bisTsTxBOKuiDhOf3BpWu2RKsOYzqN1PQFkxhEoCWjsVnm', 'USER', 'teste2');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
