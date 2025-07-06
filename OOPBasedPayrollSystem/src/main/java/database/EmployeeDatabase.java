package database;

import com.mycompany.oopbasedpayrollsystem.Employee;

import java.sql.*;
import java.sql.Date;
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
                String convertedBirthdayStr = birthday != null ? birthday.toString() : null;
String payslipNo = rs.getString("payslip_no");
java.sql.Date periodStart = rs.getDate("period_start");
java.sql.Date periodEnd = rs.getDate("period_end");
Employee emp = new Employee(id, name, firstName, lastName, position, department, email, password,
        dateHired, basicSalary, allowance, phoneNumber,
        sssNumber, philhealthNumber, pagibigNumber, tinNumber, address,
        sssDeduction, philhealthDeduction, pagibigDeduction, birTax,
        payslipNo, periodStart, periodEnd);
            

                employees.put(id, emp);
            }
            System.out.println("✅ Employees loaded from MySQL: " + employees.size());
        } catch (SQLException e) {
            System.out.println("❌ Error loading employees: " + e.getMessage());
        }
    }
public List<Employee> getAllEmployeesData() {
    List<Employee> employeeList = new ArrayList<>();
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

            // ✅ New payslip-related fields
            String payslipNo = rs.getString("payslip_no");
            java.sql.Date periodStart = rs.getDate("period_start");
            java.sql.Date periodEnd = rs.getDate("period_end");

            Employee emp = new Employee(id, name, firstName, lastName, position, department, email, password,
                    dateHired, basicSalary, allowance, phoneNumber,
                    sssNumber, philhealthNumber, pagibigNumber, tinNumber, address,
                    sssDeduction, philhealthDeduction, pagibigDeduction, birTax,
                    payslipNo, periodStart, periodEnd);

            employeeList.add(emp);
        }

        System.out.println("✅ Employees loaded from MySQL: " + employeeList.size());

    } catch (SQLException e) {
        System.out.println("❌ Error loading employees: " + e.getMessage());
    }

    return employeeList;
}

    public int validateLogin(String username, String password) {
        return employees.values().stream()
                .filter(emp -> emp.getEmail().equals(username) && emp.getPassword().equals(password))
                .map(Employee::getEmployeeID)
                .findFirst()
                .orElse(-1);
    }

    public boolean deleteEmployeeById(int id) {
        if (employees.containsKey(id)) {
            employees.remove(id);
            return true;
        }
        return false;
    }
public boolean AddEmployee(Map<String, Object> userdata) {
    try {
        String name1 = (String) userdata.get("name");
        String position2 = (String) userdata.get("position");
        String department3 = (String) userdata.get("department");
        String email4 = (String) userdata.get("email");
        String password5 = (String) userdata.get("password");
        Date dateHired6 = (Date) userdata.get("dateHired");
        double basicSalary7 = (double) userdata.get("basicSalary");
        double allowance8 = (double) userdata.get("allowance");
        String firstName9 = (String) userdata.get("firstName");
        String lastName10 = (String) userdata.get("lastName");
        Date birthday11 = (Date) userdata.get("birthday");
        String phoneNumber12 = (String) userdata.get("phoneNumber");
        String sssNumber13 = (String) userdata.get("sssNumber");
        String philhealthNumber14 = (String) userdata.get("philhealthNumber");
        String pagibigNumber15 = (String) userdata.get("pagibigNumber");
        String tinNumber16 = (String) userdata.get("tinNumber");
        String address17 = (String) userdata.get("address");
        double sssDeduction18 = (double) userdata.get("sssDeduction");
        double philhealthDeduction19 = (double) userdata.get("philhealthDeduction");
        double pagibigDeduction20 = (double) userdata.get("pagibigDeduction");
        double birTax21 = (double) userdata.get("birTax");

        // ✅ New payslip fields
        String payslipNo22 = (String) userdata.get("payslipNo");
        Date periodStart23 = (Date) userdata.get("periodStart");
        Date periodEnd24 = (Date) userdata.get("periodEnd");

        String query = "INSERT INTO employees (name, position, department, email, password, date_hired, " +
                "basic_salary, allowance, first_name, last_name, birthday, phone_number, sss_number, " +
                "philhealth_number, pagibig_number, tin_number, address, sss_deduction, philhealth_deduction, " +
                "pagibig_deduction, bir_tax, payslip_no, period_start, period_end) VALUES " +
                "(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, name1);
            pstmt.setString(2, position2);
            pstmt.setString(3, department3);
            pstmt.setString(4, email4);
            pstmt.setString(5, password5);
            pstmt.setDate(6, dateHired6);
            pstmt.setDouble(7, basicSalary7);
            pstmt.setDouble(8, allowance8);
            pstmt.setString(9, firstName9);
            pstmt.setString(10, lastName10);
            pstmt.setDate(11, birthday11);
            pstmt.setString(12, phoneNumber12);
            pstmt.setString(13, sssNumber13);
            pstmt.setString(14, philhealthNumber14);
            pstmt.setString(15, pagibigNumber15);
            pstmt.setString(16, tinNumber16);
            pstmt.setString(17, address17);
            pstmt.setDouble(18, sssDeduction18);
            pstmt.setDouble(19, philhealthDeduction19);
            pstmt.setDouble(20, pagibigDeduction20);
            pstmt.setDouble(21, birTax21);
            pstmt.setString(22, payslipNo22);
            pstmt.setDate(23, periodStart23);
            pstmt.setDate(24, periodEnd24);

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        int newId = generatedKeys.getInt(1);
                        Employee newEmployee = new Employee(newId, name1, firstName9, lastName10,
                                position2, department3, email4, password5,
                                dateHired6, basicSalary7, allowance8,
                                phoneNumber12, sssNumber13,
                                philhealthNumber14, pagibigNumber15,
                                tinNumber16, address17,
                                sssDeduction18, philhealthDeduction19,
                                pagibigDeduction20, birTax21,
                                payslipNo22, periodStart23, periodEnd24);
                        employees.put(newId, newEmployee);
                        System.out.println("✅ Employee added successfully with ID: " + newId);
                        return true;
                    }
                }
            }

        }
    } catch (Exception e) {
        System.out.println("❌ Error adding employee: " + e.getMessage());
    }
    return false;
}

    public Employee getEmployeeById(int id) {
        return employees.get(id);
    }

    public Map<Integer, Employee> getAllEmployees() {
        return employees;
    }

    public boolean deleteEmployee(int employeeID) {
        if (employees.containsKey(employeeID)) {
            employees.remove(employeeID);
            String query = "SELECT e.*, p.payslip_no, p.period_start, p.period_end FROM employees e LEFT JOIN payslips p ON e.id = p.employee_id";
            try (Connection conn = DatabaseConnection.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, employeeID);
                int affectedRows = pstmt.executeUpdate();
                if (affectedRows > 0) {
                    System.out.println("✅ Employee deleted successfully with ID: " + employeeID);
                    return true;
                }
            } catch (SQLException e) {
                System.out.println("❌ Error deleting employee: " + e.getMessage());
            }
        }
        return false;
    }

   public boolean updateEmployee(Map<String, Object> userdata) {
    try {
        int id = (int) userdata.get("id");
        String name1 = (String) userdata.get("name");
        String position2 = (String) userdata.get("position");
        String department3 = (String) userdata.get("department");
        String email4 = (String) userdata.get("email");
        String password5 = (String) userdata.get("password");
        Date dateHired6 = (Date) userdata.get("dateHired");
        double basicSalary7 = (double) userdata.get("basicSalary");
        double allowance8 = (double) userdata.get("allowance");
        String firstName9 = (String) userdata.get("firstName");
        String lastName10 = (String) userdata.get("lastName");
        Date birthday11 = (Date) userdata.get("birthday");
        String phoneNumber12 = (String) userdata.get("phoneNumber");
        String sssNumber13 = (String) userdata.get("sssNumber");
        String philhealthNumber14 = (String) userdata.get("philhealthNumber");
        String pagibigNumber15 = (String) userdata.get("pagibigNumber");
        String tinNumber16 = (String) userdata.get("tinNumber");
        String address17 = (String) userdata.get("address");
        double sssDeduction18 = (double) userdata.get("sssDeduction");
        double philhealthDeduction19 = (double) userdata.get("philhealthDeduction");
        double pagibigDeduction20 = (double) userdata.get("pagibigDeduction");
        double birTax21 = (double) userdata.get("birTax");

        // ✅ Payslip fields
        String payslipNo22 = (String) userdata.get("payslipNo");
        Date periodStart23 = (Date) userdata.get("periodStart");
        Date periodEnd24 = (Date) userdata.get("periodEnd");

        String query = "UPDATE employees SET name=?, position=?, department=?, email=?, password=?, " +
                "date_hired=?, basic_salary=?, allowance=?, first_name=?, last_name=?, birthday=?, " +
                "phone_number=?, sss_number=?, philhealth_number=?, pagibig_number=?, tin_number=?, " +
                "address=?, sss_deduction=?, philhealth_deduction=?, pagibig_deduction=?, bir_tax=?, " +
                "payslip_no=?, period_start=?, period_end=? WHERE id=?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, name1);
            pstmt.setString(2, position2);
            pstmt.setString(3, department3);
            pstmt.setString(4, email4);
            pstmt.setString(5, password5);
            pstmt.setDate(6, dateHired6);
            pstmt.setDouble(7, basicSalary7);
            pstmt.setDouble(8, allowance8);
            pstmt.setString(9, firstName9);
            pstmt.setString(10, lastName10);
            pstmt.setDate(11, birthday11);
            pstmt.setString(12, phoneNumber12);
            pstmt.setString(13, sssNumber13);
            pstmt.setString(14, philhealthNumber14);
            pstmt.setString(15, pagibigNumber15);
            pstmt.setString(16, tinNumber16);
            pstmt.setString(17, address17);
            pstmt.setDouble(18, sssDeduction18);
            pstmt.setDouble(19, philhealthDeduction19);
            pstmt.setDouble(20, pagibigDeduction20);
            pstmt.setDouble(21, birTax21);
            pstmt.setString(22, payslipNo22);
            pstmt.setDate(23, periodStart23);
            pstmt.setDate(24, periodEnd24);
            pstmt.setInt(25, id); // WHERE id=?

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                Employee updatedEmployee = new Employee(id, name1, firstName9, lastName10,
                        position2, department3, email4, password5,
                        dateHired6, basicSalary7, allowance8,
                        phoneNumber12, sssNumber13,
                        philhealthNumber14, pagibigNumber15,
                        tinNumber16, address17,
                        sssDeduction18, philhealthDeduction19,
                        pagibigDeduction20, birTax21,
                        payslipNo22, periodStart23, periodEnd24);
                employees.put(id, updatedEmployee);
                System.out.println("✅ Employee updated successfully with ID: " + id);
                return true;
            }

        } catch (SQLException e) {
            System.out.println("❌ SQL error updating employee: " + e.getMessage());
        }

    } catch (Exception e) {
        System.out.println("❌ General error updating employee: " + e.getMessage());
    }

    return false;
}
}