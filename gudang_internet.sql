-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Oct 13, 2026 at 05:53 AM
-- Server version: 8.0.30
-- PHP Version: 8.1.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `gudang_internet`
--

-- --------------------------------------------------------

--
-- Table structure for table `barang`
--

CREATE TABLE `barang` (
  `id_barang` int NOT NULL,
  `nama_barang` varchar(100) NOT NULL,
  `id_kategori` int DEFAULT NULL,
  `id_supplier` int DEFAULT NULL,
  `harga` int NOT NULL,
  `stok` int NOT NULL,
  `spesifikasi_khusus` varchar(100) DEFAULT NULL,
  `cara_penyimpanan` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `barang`
--

INSERT INTO `barang` (`id_barang`, `nama_barang`, `id_kategori`, `id_supplier`, `harga`, `stok`, `spesifikasi_khusus`, `cara_penyimpanan`) VALUES
(1, 'Kabel UTP Cat 6 (Box 300m)', 1, 1, 1250000, 10, 'Panjang: 300 meter / UTP Cat 6', 'Disimpan di rak kabel, hindari basah.'),
(2, 'Kabel Fiber Optik Dropcore 1 Core', 1, 1, 750000, 15, 'Panjang: 1000 meter / FO', 'Digulung rapi dan disimpan di drum kabel.'),
(3, 'Konektor RJ45 AMP (Isi 100 pcs)', 3, 2, 85000, 25, 'Merek: AMP / Cat 5e-6', 'Disimpan dalam box plastik di rak aksesoris.'),
(4, 'Konektor RJ45 Cat 6 Shielded', 3, 2, 150000, 10, 'Merek: CommScope', 'Disimpan di laci komponen kecil.'),
(5, 'Modem ONT ZTE F609', 2, 2, 350000, 20, 'Merek: ZTE (Garansi 12 Bulan)', 'Simpan di rak elektronik bersuhu sejuk.'),
(6, 'Router Mikrotik RB750Gr3', 2, 1, 720000, 8, 'Merek: Mikrotik (Garansi 24 Bulan)', 'Simpan di rak elektronik, hindari lembap.'),
(7, 'Switch Hub TP-Link 16 Port Gigabit', 2, 3, 450000, 5, 'Merek: TP-Link', 'Simpan di dalam kardus aslinya di rak perangkat.'),
(8, 'Tang Crimping RJ45 Heavy Duty', 4, 3, 120000, 12, 'Alat Pasang / Besi Baja', 'Digantung pada panel perkakas gudang.'),
(9, 'LAN Tester NF-468', 4, 3, 95000, 7, 'Alat Testing Kabel LAN', 'Disimpan di kotak alat ukur/testing.');

-- --------------------------------------------------------

--
-- Table structure for table `kategori`
--

CREATE TABLE `kategori` (
  `id_kategori` int NOT NULL,
  `nama_kategori` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `kategori`
--

INSERT INTO `kategori` (`id_kategori`, `nama_kategori`) VALUES
(1, 'Kabel Jaringan'),
(2, 'Perangkat Jaringan'),
(3, 'Aksesoris & Konektor'),
(4, 'Alat Kerja & Testing');

-- --------------------------------------------------------

--
-- Table structure for table `supplier`
--

CREATE TABLE `supplier` (
  `id_supplier` int NOT NULL,
  `nama_supplier` varchar(100) NOT NULL,
  `telepon` varchar(20) DEFAULT NULL,
  `alamat` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `supplier`
--

INSERT INTO `supplier` (`id_supplier`, `nama_supplier`, `telepon`, `alamat`) VALUES
(1, 'PT Mitra Global Telekomindo', '021-555789', 'Jakarta Pusat'),
(2, 'CV Jaringan Nusantara', '022-876543', 'Bandung'),
(3, 'Toko Sinar Jaya Elektrik', '031-123456', 'Surabaya');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_keluar`
--

CREATE TABLE `transaksi_keluar` (
  `id_keluar` int NOT NULL,
  `id_barang` int DEFAULT NULL,
  `jumlah_keluar` int NOT NULL,
  `tanggal_keluar` date NOT NULL,
  `tujuan_proyek` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi_keluar`
--

INSERT INTO `transaksi_keluar` (`id_keluar`, `id_barang`, `jumlah_keluar`, `tanggal_keluar`, `tujuan_proyek`) VALUES
(1, 3, 2, '2026-05-08', 'Instalasi Kantor Cabang A'),
(2, 6, 1, '2026-05-09', 'Pemasangan Pelanggan Baru RT/RW Net'),
(3, 1, 1, '2026-05-10', 'Penarikan Jalur Komplek Melati');

-- --------------------------------------------------------

--
-- Table structure for table `transaksi_masuk`
--

CREATE TABLE `transaksi_masuk` (
  `id_masuk` int NOT NULL,
  `id_barang` int DEFAULT NULL,
  `jumlah_masuk` int NOT NULL,
  `tanggal_masuk` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Dumping data for table `transaksi_masuk`
--

INSERT INTO `transaksi_masuk` (`id_masuk`, `id_barang`, `jumlah_masuk`, `tanggal_masuk`) VALUES
(1, 1, 5, '2026-05-01'),
(2, 5, 10, '2026-05-03'),
(3, 8, 5, '2026-05-05');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `barang`
--
ALTER TABLE `barang`
  ADD PRIMARY KEY (`id_barang`),
  ADD KEY `id_kategori` (`id_kategori`),
  ADD KEY `id_supplier` (`id_supplier`);

--
-- Indexes for table `kategori`
--
ALTER TABLE `kategori`
  ADD PRIMARY KEY (`id_kategori`);

--
-- Indexes for table `supplier`
--
ALTER TABLE `supplier`
  ADD PRIMARY KEY (`id_supplier`);

--
-- Indexes for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD PRIMARY KEY (`id_keluar`),
  ADD KEY `id_barang` (`id_barang`);

--
-- Indexes for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD PRIMARY KEY (`id_masuk`),
  ADD KEY `id_barang` (`id_barang`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `barang`
--
ALTER TABLE `barang`
  MODIFY `id_barang` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `kategori`
--
ALTER TABLE `kategori`
  MODIFY `id_kategori` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `supplier`
--
ALTER TABLE `supplier`
  MODIFY `id_supplier` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  MODIFY `id_keluar` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  MODIFY `id_masuk` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `barang`
--
ALTER TABLE `barang`
  ADD CONSTRAINT `barang_ibfk_1` FOREIGN KEY (`id_kategori`) REFERENCES `kategori` (`id_kategori`),
  ADD CONSTRAINT `barang_ibfk_2` FOREIGN KEY (`id_supplier`) REFERENCES `supplier` (`id_supplier`);

--
-- Constraints for table `transaksi_keluar`
--
ALTER TABLE `transaksi_keluar`
  ADD CONSTRAINT `transaksi_keluar_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);

--
-- Constraints for table `transaksi_masuk`
--
ALTER TABLE `transaksi_masuk`
  ADD CONSTRAINT `transaksi_masuk_ibfk_1` FOREIGN KEY (`id_barang`) REFERENCES `barang` (`id_barang`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
