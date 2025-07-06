package com.mycompany.oopbasedpayrollsystem;

import java.sql.*;
import java.util.*;

public class EmployeeDatabase {
    private final Map<Integer, Employee> employees = new HashMap<>();

    public EmployeeDatabase() {
        loadEmployeesFromDatabase();
    }

    private void loadEmployeesFromDatabase() {
        String query = "SELECT e.*, p.payslip_no, p.period_start, p.period_end FROM employees e LEFT JOIN payslips p ON e.id = p.employee_id";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String position = rs.getString("position");
                String department = rs.getString("department");
                String email = rs.getString("email");
                String password = rs.getString("password");
                java.sql.Date dateHired = rs.getDate("date_hired");
                double basicSalary = rs.getDouble("basic_salary");
                double allowance = rs.getDouble("allowance");
                String firstName = rs.getString("first_name");
                String lastName = rs.getString("last_name");
                java.sql.Date birthday = rs.getDate("birthday");
                String phoneNumber = rs.getString("phone_number");
                String sssNumber = rs.getString("sss_number");
                String philhealthNumber = rs.getString("philhealth_number");
                String pagibigNumber = rs.getString("pagibig_number");
                String tinNumber = rs.getString("tin_number");
                String address = rs.getString("address");
                double sssDeduction = rs.getDouble("sss_deduction");
                double philhealthDeduction = rs.getDouble("philhealth_deduction");
                double pagibigDeduction = rs.getDouble("pagibig_deduction");
                double birTax = rs.getDouble("bir_tax");
                String payslipNo = rs.getString("payslip_no");
                java.sql.Date periodStart = rs.getDate("period_start");
                java.sql.Date periodEnd = rs.getDate("period_end");

                Employee emp = new Employee(id, name, firstName, lastName, position, department, email, password,
                        dateHired, basicSalary, allowance, phoneNumber,
                        sssNumber, philhealthNumber, pagibigNumber, tinNumber, address,
                        sssDeduction, philhealthDeduction, pagibigDeduction, birTax, payslipNo, periodStart, periodEnd);

                employees.put(id, emp);
            }
            System.out.println("✅ Employees loaded from MySQL: " + employees.size());
        } catch (SQLException e) {
            System.out.println("❌ Error loading employees: " + e.getMessage());
        }
    }

    public int validateLogin(String username, String password) {
        return employees.values().stream()
                .filter(emp -> emp.getEmail().equals(username) && emp.getPassword().equals(password))
                .map(Employee::getEmployeeID)
                .findFirst()
                .orElse(-1);
    }

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    public Map<Integer, Employee> getAllEmployees() {
        return employees;
    }
}