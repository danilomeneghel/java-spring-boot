-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: 27-Out-2018 às 00:54
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
  `courseid` bigint(20) NOT NULL AUTO_INCREMENT,
  `coursename` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`courseid`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `course`
--

INSERT INTO `course` (`courseid`, `coursename`) VALUES
(1, 'Programming Logic'),
(2, 'Programming'),
(3, 'Data Structure'),
(4, 'Database'),
(5, 'Software Engineering'),
(6, 'People Management');

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
(9),
(9);

-- --------------------------------------------------------

--
-- Estrutura da tabela `student`
--

DROP TABLE IF EXISTS `student`;
CREATE TABLE IF NOT EXISTS `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `department` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `student`
--

INSERT INTO `student` (`id`, `department`, `email`, `firstname`, `lastname`) VALUES
(1, 'TI', 'john@mayer.com', 'John', 'Mayer'),
(2, 'RH', 'mary@jane.com', 'Mary', 'Jane'),
(3, 'TI', 'stephany@alba.com', 'Stephany', 'Alba');

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
(1, 1),
(1, 2),
(1, 3),
(2, 6),
(3, 5);

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
  `password_hash` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_sb8bbouer5wak8vyiiy4pf2bx` (`username`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `user`
--

INSERT INTO `user` (`id`, `password`, `role`, `username`, `password_hash`) VALUES
(1, '$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6', 'USER', 'user', NULL),
(2, '$2a$08$bCCcGjB03eulCWt3CY0AZew2rVzXFyouUolL5dkL/pBgFkUH9O4J2', 'ADMIN', 'admin', NULL),
(7, '$2a$10$bvAraSaoVY.s0n8d0381Wu/W0u7YOhcd17vXRI370xQuKORzBXSIm', 'ADMIN', 'teste2', NULL),
(8, '$2a$10$nxBjvzGTKabRPT0QjQstEOAPxYkMmieE3LEbXgHiEFjA05Xsz1oWu', 'USER', 'teste3', NULL);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
