-- phpMyAdmin SQL Dump
-- version 4.9.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 12, 2019 at 03:25 AM
-- Server version: 10.3.16-MariaDB
-- PHP Version: 7.3.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bimbelkita`
--

-- --------------------------------------------------------

--
-- Table structure for table `guru`
--

CREATE TABLE `guru` (
  `guru_id` int(11) NOT NULL,
  `nip` int(11) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `bidang` varchar(100) NOT NULL,
  `kelamin` enum('L','P') NOT NULL,
  `alamat` text NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `foto` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `guru`
--

INSERT INTO `guru` (`guru_id`, `nip`, `nama`, `bidang`, `kelamin`, `alamat`, `tanggal_lahir`, `foto`) VALUES
(1, 1, 'Ega', 'matematika', 'L', 'malang', '2019-10-08', ''),
(2, 2, 'Rafi', 'matematika', 'L', 'malang', '2019-11-19', ''),
(3, 312, 'reza', 'bhs. indonesia', 'L', 'malang', '2019-11-07', ''),
(4, 4, 'rezas', 'Bhs. Indonesia', 'P', 'malang', '2019-11-06', 'Slide3.PNG'),
(5, 12, 'epp', 'ipa', 'L', 'malang', '2019-11-07', '706c5bea-2e3a-4933-a187-102f6a08eb71.PNG'),
(8, 987321, 'Melda', 'IPA', 'P', 'Malang', '1988-11-11', '1c7f2e3c-e557-4f21-a0c6-cbca9010340e.jpg'),
(9, 134, '123', 'IPA', 'L', '123', '2019-11-07', '60a82954-6618-47c4-ae3f-c45b9e2848bb.PNG');

-- --------------------------------------------------------

--
-- Table structure for table `jadwal`
--

CREATE TABLE `jadwal` (
  `id` int(11) NOT NULL,
  `id_guru` int(11) NOT NULL,
  `id_kelas` int(11) NOT NULL,
  `ruang` int(11) NOT NULL,
  `hari` varchar(10) NOT NULL,
  `jam` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jadwal`
--

INSERT INTO `jadwal` (`id`, `id_guru`, `id_kelas`, `ruang`, `hari`, `jam`) VALUES
(4, 2, 2, 3, 'senin', '18:00 - 20:00'),
(5, 4, 1, 3, 'senin', '15:00 - 17:00'),
(6, 1, 1, 1, 'rabu', '15:00 - 17:00'),
(7, 5, 1, 1, 'selasa', '15:00 - 17:00'),
(8, 8, 1, 1, 'senin', '18:00 - 20:00');

-- --------------------------------------------------------

--
-- Table structure for table `kelas`
--

CREATE TABLE `kelas` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL,
  `jumlah_siswa` int(11) NOT NULL DEFAULT 0,
  `tingkat` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kelas`
--

INSERT INTO `kelas` (`id`, `nama`, `jumlah_siswa`, `tingkat`) VALUES
(1, '7', 1, 7),
(2, '8', 3, 8),
(3, '9', 1, 9),
(4, '7a', 0, 7),
(5, '8a', 0, 8),
(10, '7b', 0, 7);

-- --------------------------------------------------------

--
-- Table structure for table `ruang`
--

CREATE TABLE `ruang` (
  `id` int(11) NOT NULL,
  `nama` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ruang`
--

INSERT INTO `ruang` (`id`, `nama`) VALUES
(1, '1'),
(2, '2a'),
(3, '3'),
(4, '7a'),
(5, '34');

-- --------------------------------------------------------

--
-- Table structure for table `siswa`
--

CREATE TABLE `siswa` (
  `siswa_id` int(11) NOT NULL,
  `NIS` int(16) NOT NULL,
  `nama` varchar(100) NOT NULL,
  `kelas` int(11) NOT NULL,
  `kelamin` enum('L','P') NOT NULL,
  `alamat` text NOT NULL,
  `tanggal_lahir` date NOT NULL,
  `foto` varchar(100) NOT NULL,
  `kelas_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `siswa`
--

INSERT INTO `siswa` (`siswa_id`, `NIS`, `nama`, `kelas`, `kelamin`, `alamat`, `tanggal_lahir`, `foto`, `kelas_id`) VALUES
(1, 8612387, 'Valent Wastu', 7, 'L', 'Batu', '2001-10-04', 'siswa.png', 1),
(3, 81523765, 'Jhossie', 8, 'L', 'Polehan', '2001-11-05', '8acc01eb-f9d4-4210-a66f-5b90fbadc264.png', 2),
(5, 8793219, 'Yudas', 9, 'L', 'kjshadj', '2019-11-25', 'af9648e6-bc81-412f-b077-f9d8f6dbf320.PNG', 3);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(60) NOT NULL,
  `password` varchar(60) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `password`) VALUES
(0, 'admin', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `guru`
--
ALTER TABLE `guru`
  ADD PRIMARY KEY (`guru_id`);

--
-- Indexes for table `jadwal`
--
ALTER TABLE `jadwal`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `kelas`
--
ALTER TABLE `kelas`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `nama` (`nama`);

--
-- Indexes for table `ruang`
--
ALTER TABLE `ruang`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `siswa`
--
ALTER TABLE `siswa`
  ADD PRIMARY KEY (`siswa_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `guru`
--
ALTER TABLE `guru`
  MODIFY `guru_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `jadwal`
--
ALTER TABLE `jadwal`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `kelas`
--
ALTER TABLE `kelas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `ruang`
--
ALTER TABLE `ruang`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `siswa`
--
ALTER TABLE `siswa`
  MODIFY `siswa_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
