-- Create Database
CREATE DATABASE IF NOT EXISTS payroll_db;
USE payroll_db;

-- ADMIN CREDENTIALS
-- ----------------------------
CREATE TABLE admin_credentials (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL
);

INSERT INTO admin_credentials (ID, username, password) VALUES
(1,'admin', 'admin123');


-- DEPARTMENTS & POSITIONS
-- ----------------------------
CREATE TABLE departments (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);


CREATE TABLE positions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    department_id INT,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

INSERT INTO departments (ID, name) VALUES (1,'HR');
INSERT INTO positions (ID, title, department_id) VALUES (1, 'Clerk', 1);

-- EMPLOYEES & RELATED DATA
-- ----------------------------
CREATE TABLE employees (
    id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    position_id INT,
    birthday DATE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255),
    date_hired DATE,
    basic_salary DOUBLE DEFAULT 0,
    allowance DOUBLE DEFAULT 0,
    phone_number VARCHAR(20),
    address VARCHAR(255),
    FOREIGN KEY (position_id) REFERENCES positions(id)
);

CREATE TABLE employee_identification (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    sss_number VARCHAR(50),
    philhealth_number VARCHAR(50),
    pagibig_number VARCHAR(50),
    tin_number VARCHAR(50),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


-- BENEFITS & DEDUCTIONS (Per Employee)
-- ----------------------------
CREATE TABLE benefits (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    rice_subsidy DOUBLE DEFAULT 0,
    phone_allowance DOUBLE DEFAULT 0,
    clothing_allowance DOUBLE DEFAULT 0,
    total_benefits DOUBLE DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

CREATE TABLE deductions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    sss DOUBLE DEFAULT 0,
    philhealth DOUBLE DEFAULT 0,
    pagibig DOUBLE DEFAULT 0,
    bir_tax DOUBLE DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


-- ATTENDANCE & LEAVES
-- ----------------------------
CREATE TABLE attendance (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    date DATE,
    time_in TIME,
    time_out TIME,
    FOREIGN KEY (employee_id) REFERENCES employees(id) ON DELETE SET NULL
);

CREATE TABLE leave_applications (
    id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT NOT NULL,
    leave_type VARCHAR(50),
    start_date DATE,
    end_date DATE,
    status VARCHAR(50),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


-- PAYSLIPS (No Calculated Fields)
-- ----------------------------
CREATE TABLE payslips (
    id INT AUTO_INCREMENT PRIMARY KEY,
    payslip_no VARCHAR(50) NOT NULL,
    employee_id INT NOT NULL,
    period_start DATE NOT NULL,
    period_end DATE NOT NULL,
    monthly_rate DOUBLE DEFAULT 0,
    daily_rate DOUBLE DEFAULT 0,
    days_worked INT DEFAULT 0,
    overtime_hours DOUBLE DEFAULT 0,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);


-- SAMPLE DATA
-- ----------------------------
INSERT INTO employees (ID, first_name, last_name, position_id, birthday, email, password, date_hired, basic_salary, allowance, phone_number, address) VALUES
(1,'Juan', 'Dela Cruz', 1, '1995-03-15', 'juan', 'juan123', '2025-06-21', 20000, 5000, '09171234567', 'Pasig, Metro Manila');

INSERT INTO employee_identification (ID, employee_id, sss_number, philhealth_number, pagibig_number, tin_number) VALUES
(1, 1, '34-5678912-1', '700000000000001', '1234-5678-9012', '987-654-321-000');

INSERT INTO benefits (ID, employee_id, rice_subsidy, phone_allowance, clothing_allowance, total_benefits) VALUES
(1, 1, 1500, 1000, 500, 3000);

INSERT INTO deductions (ID, employee_id, sss, philhealth, pagibig, bir_tax) VALUES
(1, 1, 900, 600, 100, 800);

INSERT INTO payslips (ID, payslip_no, employee_id, period_start, period_end, monthly_rate, daily_rate, days_worked, overtime_hours) VALUES
(1, 'PS-2025-07-15', 1, '2025-07-01', '2025-07-15', 20000, 909.09, 10, 2);


-- VIEW: Payroll Summary
-- ----------------------------
CREATE OR REPLACE VIEW vw_payroll_summary AS
SELECT 
    e.id AS employee_id,
    CONCAT(e.first_name, ' ', e.last_name) AS full_name,
    p.payslip_no,
    p.period_start,
    p.period_end,
    (p.daily_rate * p.days_worked + (p.overtime_hours * p.daily_rate / 8)) AS gross_income,
    b.total_benefits,
    (d.sss + d.philhealth + d.pagibig + d.bir_tax) AS total_deductions,
    ((p.daily_rate * p.days_worked + (p.overtime_hours * p.daily_rate / 8)) + b.total_benefits - (d.sss + d.philhealth + d.pagibig + d.bir_tax)) AS take_home_pay
FROM payslips p
JOIN employees e ON p.employee_id = e.id
JOIN benefits b ON b.employee_id = e.id
JOIN deductions d ON d.employee_id = e.id;


-- STORED PROCEDURE: Generate Payslip
-- ----------------------------
DELIMITER //
CREATE PROCEDURE generate_payslip(IN emp_id INT, IN start_date DATE, IN end_date DATE)
BEGIN
    DECLARE salary DOUBLE;
    DECLARE daily DOUBLE;
    DECLARE days INT DEFAULT 15;

    SELECT basic_salary INTO salary FROM employees WHERE id = emp_id;
    SET daily = salary / 22;

    INSERT INTO payslips (payslip_no, employee_id, period_start, period_end, monthly_rate, daily_rate, days_worked, overtime_hours)
    VALUES (CONCAT('PS-', emp_id, '-', DATE_FORMAT(NOW(), '%Y%m%d')), emp_id, start_date, end_date, salary, daily, days, 0);
END //
DELIMITER ;


