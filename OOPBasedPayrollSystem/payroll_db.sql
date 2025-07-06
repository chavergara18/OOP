-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jul 06, 2025 at 06:09 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `payroll_db`
--

-- --------------------------------------------------------

--
-- Table structure for table `admin_credentials`
--

CREATE TABLE `admin_credentials` (
  `id` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `admin_credentials`
--

INSERT INTO `admin_credentials` (`id`, `username`, `password`) VALUES
(1, 'admin', 'admin123');

-- --------------------------------------------------------

--
-- Table structure for table `attendance`
--

CREATE TABLE `attendance` (
  `id` int(11) NOT NULL,
  `employee_id` int(11) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `time_in` time DEFAULT NULL,
  `time_out` time DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `id` int(11) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `name` varchar(100) NOT NULL,
  `position` varchar(100) DEFAULT NULL,
  `department` varchar(100) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `date_hired` date DEFAULT NULL,
  `basic_salary` double DEFAULT 0,
  `allowance` double DEFAULT 0,
  `phone_number` varchar(20) DEFAULT NULL,
  `sss_number` varchar(50) DEFAULT NULL,
  `philhealth_number` varchar(50) DEFAULT NULL,
  `pagibig_number` varchar(50) DEFAULT NULL,
  `tin_number` varchar(50) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `sss_deduction` double DEFAULT 0,
  `philhealth_deduction` double DEFAULT 0,
  `pagibig_deduction` double DEFAULT 0,
  `bir_tax` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`id`, `first_name`, `last_name`, `name`, `position`, `department`, `birthday`, `email`, `password`, `date_hired`, `basic_salary`, `allowance`, `phone_number`, `sss_number`, `philhealth_number`, `pagibig_number`, `tin_number`, `address`, `sss_deduction`, `philhealth_deduction`, `pagibig_deduction`, `bir_tax`) VALUES
(1, 'Juan', 'Dela Cruz', 'Juan Dela Cruz', 'Clerk', 'HR', '1995-03-15', 'juan', 'juan123', '2025-06-21', 20000, 5000, '09171234567', '34-5678912-1', '700000000000001', '1234-5678-9012', '987-654-321-000', 'Pasig, Metro Manila', 0, 0, 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `leave_applications`
--

CREATE TABLE `leave_applications` (
  `id` int(11) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `leave_type` varchar(50) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `status` varchar(50) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `leave_applications`
--

INSERT INTO `leave_applications` (`id`, `employee_id`, `leave_type`, `start_date`, `end_date`, `status`) VALUES
(1, 1, 'Sick Leave', '2025-10-15', '2025-10-15', 'Pending');

-- --------------------------------------------------------

--
-- Table structure for table `payslips`
--

CREATE TABLE `payslips` (
  `id` int(11) NOT NULL,
  `payslip_no` varchar(50) NOT NULL,
  `employee_id` int(11) NOT NULL,
  `period_start` date NOT NULL,
  `period_end` date NOT NULL,
  `monthly_rate` double DEFAULT 0,
  `daily_rate` double DEFAULT 0,
  `days_worked` int(11) DEFAULT 0,
  `overtime_hours` double DEFAULT 0,
  `gross_income` double DEFAULT 0,
  `rice_subsidy` double DEFAULT 0,
  `phone_allowance` double DEFAULT 0,
  `clothing_allowance` double DEFAULT 0,
  `total_benefits` double DEFAULT 0,
  `sss` double DEFAULT 0,
  `philhealth` double DEFAULT 0,
  `pagibig` double DEFAULT 0,
  `withholding_tax` double DEFAULT 0,
  `total_deductions` double DEFAULT 0,
  `take_home_pay` double DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `payslips`
--

INSERT INTO `payslips` (`id`, `payslip_no`, `employee_id`, `period_start`, `period_end`, `monthly_rate`, `daily_rate`, `days_worked`, `overtime_hours`, `gross_income`, `rice_subsidy`, `phone_allowance`, `clothing_allowance`, `total_benefits`, `sss`, `philhealth`, `pagibig`, `withholding_tax`, `total_deductions`, `take_home_pay`) VALUES
(1, '31-2023-12-30', 1, '2023-12-18', '2023-12-31', 20000, 1000, 10, 2, 12000, 1500, 2000, 1000, 4500, 500, 300, 100, 0, 900, 15600);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admin_credentials`
--
ALTER TABLE `admin_credentials`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `attendance`
--
ALTER TABLE `attendance`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `leave_applications`
--
ALTER TABLE `leave_applications`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- Indexes for table `payslips`
--
ALTER TABLE `payslips`
  ADD PRIMARY KEY (`id`),
  ADD KEY `employee_id` (`employee_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `admin_credentials`
--
ALTER TABLE `admin_credentials`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `attendance`
--
ALTER TABLE `attendance`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `leave_applications`
--
ALTER TABLE `leave_applications`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `payslips`
--
ALTER TABLE `payslips`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendance`
--
ALTER TABLE `attendance`
  ADD CONSTRAINT `attendance_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `leave_applications`
--
ALTER TABLE `leave_applications`
  ADD CONSTRAINT `leave_applications_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);

--
-- Constraints for table `payslips`
--
ALTER TABLE `payslips`
  ADD CONSTRAINT `payslips_ibfk_1` FOREIGN KEY (`employee_id`) REFERENCES `employees` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
