-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Nov 11, 2013 at 06:07 PM
-- Server version: 5.5.8
-- PHP Version: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `test`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE IF NOT EXISTS `attendance` (
  `date` date NOT NULL,
  `subject` varchar(10) NOT NULL,
  `id` int(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `attendance`
--

INSERT INTO `attendance` (`date`, `subject`, `id`) VALUES
('2013-11-05', 'OS', NULL),
('2013-11-04', 'ITC', NULL),
('2013-11-04', 'OSLAB', 121070040),
('2013-11-05', 'CPLAB', 121070040),
('2013-11-11', 'OSLAB', 121070040),
('2013-11-05', 'CP-2', 12345),
('2013-11-05', 'CP-2', 121070034),
('2013-11-04', 'EVS', 12345),
('2013-11-08', 'OSLAB', 12345),
('2013-11-08', 'ITC', 12345),
('2013-11-08', 'ITC', 121070040),
('2013-11-08', 'DSA', 12345),
('2013-11-08', 'DSA', 121070040),
('2013-11-08', 'DSA', 121070034),
('2013-11-08', 'DSA', 121070035),
('2013-11-08', 'ITCLAB', 12345),
('2013-11-08', 'ITCLAB', 121070040),
('2013-11-07', 'MATHS', 121070040),
('2013-11-07', 'MATHS', 121070034),
('2013-11-07', 'MATHS', 121070035),
('2013-11-07', 'IEML', 121070040),
('2013-11-04', 'OS', 121070035),
('2013-11-05', 'EVS', NULL),
('2013-11-06', 'EVS', 12345),
('2013-11-06', 'EVS', 121070040),
('2013-11-06', 'EVS', 121070034),
('2013-11-06', 'EVS', 121070035),
('2013-11-06', 'MATHS', 12345),
('2013-11-06', 'MATHS', 121070035),
('2013-11-06', 'OS', 12345),
('2013-11-06', 'OS', 121070040),
('2013-11-06', 'DSA', NULL),
('2013-11-07', 'CP-2', 12345),
('2013-11-07', 'CP-2', 121070034),
('2013-11-08', 'OS', NULL),
('2013-02-05', 'OSLAB', 121070035),
('2013-05-06', 'CP-2', NULL),
('2013-04-05', 'CPLAB', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE IF NOT EXISTS `register` (
  `name` varchar(30) NOT NULL,
  `type` varchar(10) NOT NULL,
  `username` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  `id` int(11) DEFAULT NULL,
  `subjects` set('MATHS','CP-2','DSA','OS','ITC','EVS','CPLAB','OSLAB','ITCLAB','IEML') DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `password` (`password`),
  UNIQUE KEY `password_2` (`password`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`name`, `type`, `username`, `password`, `id`, `subjects`) VALUES
('aditya', 'student', 'aditya', 'aditya', 121070033, NULL),
('avni', 'staff', 'avni', 'avni', 0, 'DSA,OS,OSLAB'),
('biljith', 'student', 'biljith', 'biljith', 121070006, NULL),
('chinmay', 'student', 'chinmay', 'chinmay', 121070003, NULL),
('rahul', 'student', 'iyer', 'iyer', 12107002, NULL),
('jyoti', 'staff', 'jyoti', 'jyoti', 0, 'EVS'),
('prasad', 'staff', 'prasad', 'prasad', 0, 'ITC,ITCLAB'),
('pratik', 'student', 'pratik', 'pratik', 121070034, NULL),
('rahul', 'student', 'rahul', 'rahul', 121070035, NULL),
('sagar', 'staff', 'sagar', 'sagar', 0, 'IEML'),
('sagarsingh', 'student', 'sagarsingh', 'sagarsingh', 121070036, NULL),
('sahil', 'student', 'sahil', 'sahil', 121070004, NULL),
('sarita', 'staff', 'sarita', 'sarita', 0, 'MATHS'),
('seema', 'staff', 'seema', 'seema', 0, 'DSA'),
('shalini', 'staff', 'shalini', 'shalini', 0, 'MATHS'),
('sidhesh', 'student', 'sidhesh', 'sidhesh', 12107001, NULL),
('tejas', 'student', 'tejas', 'tejas', 121070005, NULL),
('zubeda', 'staff', 'zubeda', 'zubeda', 0, 'CP-2,CPLAB');
