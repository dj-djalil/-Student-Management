-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 27, 2020 at 10:39 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `db_concours`
--

-- --------------------------------------------------------

--
-- Table structure for table `domaine`
--

CREATE TABLE `domaine` (
  `NumD` int(11) NOT NULL,
  `NomD` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `domaine`
--

INSERT INTO `domaine` (`NumD`, `NomD`) VALUES
(1, 'MI'),
(2, 'STAPS'),
(3, 'SHS'),
(4, 'SEGC');

-- --------------------------------------------------------

--
-- Table structure for table `etudiant`
--

CREATE TABLE `etudiant` (
  `NumE` int(11) NOT NULL,
  `NomE` varchar(40) NOT NULL,
  `PrenomE` varchar(40) NOT NULL,
  `Moyenne` double NOT NULL,
  `DateN` date NOT NULL,
  `NumS` int(11) NOT NULL,
  `Observation` varchar(100) NOT NULL DEFAULT 'Non admis'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `etudiant`
--

INSERT INTO `etudiant` (`NumE`, `NomE`, `PrenomE`, `Moyenne`, `DateN`, `NumS`, `Observation`) VALUES
(251, 'N211', 'P211', 15.525, '1996-12-24', 11, 'Non admis'),
(252, 'N212', 'P212', 13.528, '1996-06-27', 11, 'Non admis'),
(253, 'N213', 'P213', 12.743, '1996-03-08', 11, 'Non admis'),
(254, 'N214', 'P214', 17.114, '1996-01-04', 11, 'Non admis'),
(255, 'N215', 'P215', 10.886, '1996-02-09', 11, 'Non admis'),
(256, 'N216', 'P216', 14.796, '1996-08-05', 11, 'Non admis'),
(257, 'N217', 'P217', 16.742, '1996-12-16', 11, 'Non admis'),
(259, 'N219', 'P219', 18.228, '1996-06-07', 11, 'Non admis'),
(260, 'N220 modifer', 'P220', 10, '1998-10-08', 11, 'Non admis'),
(261, 'N221', 'P221', 13.417, '1996-11-28', 11, 'Non admis'),
(262, 'N222', 'P222', 16.052, '1996-11-22', 11, 'Non admis'),
(263, 'N223', 'P223', 15.484, '1996-12-21', 11, 'Non admis'),
(264, 'N224', 'P224', 17.386, '1996-02-22', 11, 'Non admis'),
(265, 'N225', 'P225', 12.13, '1996-09-29', 11, 'Non admis'),
(266, 'N226', 'P226', 10.453, '1996-08-28', 11, 'Non admis'),
(267, 'N227', 'P227', 14.711, '1996-09-20', 11, 'Non admis'),
(268, 'N228', 'P228', 11.975, '1996-01-14', 11, 'Non admis'),
(269, 'N229', 'P229', 13.09, '1996-01-07', 11, 'Non admis'),
(270, 'N230', 'P230', 11.563, '1996-12-22', 11, 'Non admis');

-- --------------------------------------------------------

--
-- Table structure for table `filiere`
--

CREATE TABLE `filiere` (
  `NumF` int(11) NOT NULL,
  `NomF` varchar(100) NOT NULL,
  `NumD` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `filiere`
--

INSERT INTO `filiere` (`NumF`, `NomF`, `NumD`) VALUES
(1, 'Informatique', 1),
(2, 'Mathématiques', 1),
(4, 'Sociologie', 3),
(5, 'Psychologie', 3),
(6, 'Sciences économiques', 4),
(7, 'Sciences de gestion', 4);

-- --------------------------------------------------------

--
-- Table structure for table `specialite`
--

CREATE TABLE `specialite` (
  `NumS` int(11) NOT NULL,
  `NomS` varchar(40) NOT NULL,
  `NumF` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `specialite`
--

INSERT INTO `specialite` (`NumS`, `NomS`, `NumF`) VALUES
(1, 'SITW', 1),
(2, 'GL', 1),
(3, 'RSD', 1),
(4, 'STIC', 1),
(5, 'Mathématiques financières', 2),
(6, 'Equations différentielles', 2),
(7, 'Psychologie scolaire', 5),
(8, 'Economie  douanière', 6),
(9, 'Economie Internationale', 6),
(10, 'Management', 7),
(11, 'Entreprenariat', 7);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `domaine`
--
ALTER TABLE `domaine`
  ADD PRIMARY KEY (`NumD`);

--
-- Indexes for table `etudiant`
--
ALTER TABLE `etudiant`
  ADD PRIMARY KEY (`NumE`);

--
-- Indexes for table `filiere`
--
ALTER TABLE `filiere`
  ADD PRIMARY KEY (`NumF`);

--
-- Indexes for table `specialite`
--
ALTER TABLE `specialite`
  ADD PRIMARY KEY (`NumS`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `etudiant`
--
ALTER TABLE `etudiant`
  MODIFY `NumE` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=274;

--
-- AUTO_INCREMENT for table `filiere`
--
ALTER TABLE `filiere`
  MODIFY `NumF` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT for table `specialite`
--
ALTER TABLE `specialite`
  MODIFY `NumS` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
