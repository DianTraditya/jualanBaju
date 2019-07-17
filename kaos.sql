-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 17, 2019 at 07:05 AM
-- Server version: 10.1.37-MariaDB
-- PHP Version: 7.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `kaos`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbl_beli`
--

CREATE TABLE `tbl_beli` (
  `nomor` varchar(100) NOT NULL,
  `kd_produk` varchar(100) NOT NULL,
  `nm_produk` varchar(100) NOT NULL,
  `jumlah` varchar(100) NOT NULL,
  `harga` varchar(100) NOT NULL,
  `total` varchar(100) NOT NULL,
  `ubayar` varchar(100) NOT NULL,
  `ukembali` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_beli`
--

INSERT INTO `tbl_beli` (`nomor`, `kd_produk`, `nm_produk`, `jumlah`, `harga`, `total`, `ubayar`, `ukembali`) VALUES
('01', '001', 'Kaos', '2', '20000', '40000', '100000', '60000'),
('02', '002', 'Celana', '1', '120000', '120000', '150000', '30000');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_catalog`
--

CREATE TABLE `tbl_catalog` (
  `kd_Produk` varchar(100) NOT NULL,
  `kd_Kategori` varchar(100) NOT NULL,
  `nm_Produk` varchar(100) NOT NULL,
  `Harga` varchar(50) NOT NULL,
  `Stok` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_catalog`
--

INSERT INTO `tbl_catalog` (`kd_Produk`, `kd_Kategori`, `nm_Produk`, `Harga`, `Stok`) VALUES
('01', '001', 'Jaket Denim', '150000', '3'),
('02', '002', 'Hoodie X', '125000', '5'),
('03', '003', 'Kaos Rown', '98000', '13'),
('04', '004', 'Vans', '120000', '12'),
('05', '005', 'Levis Jeans', '230000', '6'),
('06', '006', 'Adidas Shoes', '500000', '2'),
('07', '007', 'Vans Shoes', '340000', '4');

-- --------------------------------------------------------

--
-- Table structure for table `tbl_login`
--

CREATE TABLE `tbl_login` (
  `id` int(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  `level` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbl_login`
--

INSERT INTO `tbl_login` (`id`, `username`, `password`, `level`) VALUES
(12345, 'admin', 'admin', 'admin'),
(54321, 'user', 'user', 'user');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `tbl_login`
--
ALTER TABLE `tbl_login`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `tbl_login`
--
ALTER TABLE `tbl_login`
  MODIFY `id` int(30) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=54322;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
