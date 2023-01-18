-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Mar 21, 2020 at 11:16 AM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.2.27

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `airlines`
--

-- --------------------------------------------------------

--
-- Table structure for table `airplanes`
--

CREATE TABLE `airplanes` (
  `id` int(11) NOT NULL,
  `type` varchar(11) NOT NULL,
  `pilot_count` int(11) NOT NULL,
  `assistant_pilot_count` int(11) NOT NULL,
  `stewardess_count` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `airplanes`
--

INSERT INTO `airplanes` (`id`, `type`, `pilot_count`, `assistant_pilot_count`, `stewardess_count`) VALUES
(1, 'Private', 2, 0, 4),
(2, 'Business', 2, 1, 4),
(3, 'Regular', 2, 2, 8);

-- --------------------------------------------------------

--
-- Table structure for table `capacity`
--

CREATE TABLE `capacity` (
  `airplane_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `passenger_on_board` int(11) DEFAULT NULL,
  `maximum_capacity` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `capacity`
--

INSERT INTO `capacity` (`airplane_id`, `destination_id`, `passenger_on_board`, `maximum_capacity`) VALUES
(1, 1, 6, 20),
(1, 2, 6, 20),
(1, 3, 6, 20),
(1, 4, 6, 20),
(1, 5, 6, 20),
(1, 6, 6, 20),
(1, 7, 6, 20),
(1, 8, 6, 20),
(1, 9, 6, 20),
(1, 10, 6, 20),
(2, 1, 7, 30),
(2, 2, 7, 30),
(2, 3, 7, 30),
(2, 4, 7, 30),
(2, 5, 7, 30),
(2, 6, 7, 30),
(2, 7, 17, 30),
(2, 8, 27, 30),
(2, 9, 7, 30),
(2, 10, 7, 30),
(3, 1, 52, 100),
(3, 2, 52, 100),
(3, 3, 52, 100),
(3, 4, 52, 100),
(3, 5, 52, 100),
(3, 6, 52, 100),
(3, 7, 52, 100),
(3, 8, 52, 100),
(3, 9, 52, 100),
(3, 10, 52, 100);

-- --------------------------------------------------------

--
-- Table structure for table `destinations`
--

CREATE TABLE `destinations` (
  `id` int(11) NOT NULL,
  `type` varchar(15) NOT NULL,
  `from_this` varchar(12) NOT NULL,
  `to_this` varchar(12) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `destinations`
--

INSERT INTO `destinations` (`id`, `type`, `from_this`, `to_this`) VALUES
(1, 'Local', 'Manila', 'Batanes'),
(2, 'Local', 'Batanes', 'Manila'),
(3, 'Local', 'Manila', 'Palawan'),
(4, 'Local', 'Palawan', 'Manila'),
(5, 'International', 'Manila', 'South Korea'),
(6, 'International', 'South Korea', 'Manila'),
(7, 'International', 'Manila', 'Japan'),
(8, 'International', 'Japan', 'Manila'),
(9, 'International', 'Manila', 'Vietnam'),
(10, 'International', 'Vietnam', 'Manila');

-- --------------------------------------------------------

--
-- Table structure for table `passengers`
--

CREATE TABLE `passengers` (
  `id` int(11) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `middlename` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `age` int(11) NOT NULL,
  `receipt_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- --------------------------------------------------------

--
-- Table structure for table `receipts`
--

CREATE TABLE `receipts` (
  `receipt_id` int(11) NOT NULL,
  `airplane_id` int(11) NOT NULL,
  `destination_id` int(11) NOT NULL,
  `baggage_fee` decimal(20,2) NOT NULL,
  `travel_tax_charge` decimal(20,2) NOT NULL,
  `destination_fee` decimal(20,2) NOT NULL,
  `travel_insurance_fee` decimal(20,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `airplanes`
--
ALTER TABLE `airplanes`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `capacity`
--
ALTER TABLE `capacity`
  ADD PRIMARY KEY (`airplane_id`,`destination_id`),
  ADD KEY `airplane_id` (`airplane_id`),
  ADD KEY `destination_id` (`destination_id`);

--
-- Indexes for table `destinations`
--
ALTER TABLE `destinations`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `passengers`
--
ALTER TABLE `passengers`
  ADD PRIMARY KEY (`id`),
  ADD KEY `receipt_id` (`receipt_id`) USING BTREE;

--
-- Indexes for table `receipts`
--
ALTER TABLE `receipts`
  ADD PRIMARY KEY (`receipt_id`),
  ADD KEY `airplane_id` (`airplane_id`,`destination_id`),
  ADD KEY `destination_id` (`destination_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `airplanes`
--
ALTER TABLE `airplanes`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `destinations`
--
ALTER TABLE `destinations`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `passengers`
--
ALTER TABLE `passengers`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `receipts`
--
ALTER TABLE `receipts`
  MODIFY `receipt_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `capacity`
--
ALTER TABLE `capacity`
  ADD CONSTRAINT `capacity_ibfk_1` FOREIGN KEY (`airplane_id`) REFERENCES `airplanes` (`id`),
  ADD CONSTRAINT `capacity_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destinations` (`id`);

--
-- Constraints for table `passengers`
--
ALTER TABLE `passengers`
  ADD CONSTRAINT `passengers_ibfk_1` FOREIGN KEY (`receipt_id`) REFERENCES `receipts` (`receipt_id`);

--
-- Constraints for table `receipts`
--
ALTER TABLE `receipts`
  ADD CONSTRAINT `receipts_ibfk_1` FOREIGN KEY (`airplane_id`) REFERENCES `airplanes` (`id`),
  ADD CONSTRAINT `receipts_ibfk_2` FOREIGN KEY (`destination_id`) REFERENCES `destinations` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
