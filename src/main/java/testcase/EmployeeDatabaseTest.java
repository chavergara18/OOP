package testcase;

import com.mycompany.oopbasedpayrollsystem.Employee;
import com.mycompany.oopbasedpayrollsystem.EmployeeDatabase;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeDatabaseTest {
    private EmployeeDatabase employeeDatabase;


    @BeforeEach
    void setUp() {
        // Initialize EmployeeDatabase (assumes database connection is properly mocked or configured)
        employeeDatabase = new EmployeeDatabase();
    }

    @Test
    void testLoadEmployeesFromDatabase() {
        // Verify employees are loaded
        Map<Integer, Employee> employees = employeeDatabase.getAllEmployees();
        assertNotNull(employees, "Employees map should not be null");
        assertFalse(employees.isEmpty(), "Employees map should not be empty");
    }

    @Test
    void testValidateLogin() {
        // Test valid login
        int employeeId = employeeDatabase.validateLogin("test@example.com", "password123");
        assertTrue(employeeId > 0, "Valid login should return a positive employee ID");

        // Test invalid login
        int invalidEmployeeId = employeeDatabase.validateLogin("invalid@example.com", "wrongpassword");
        assertEquals(-1, invalidEmployeeId, "Invalid login should return -1");


    }

    @Test
    void testGetEmployeeById() {
        // Test retrieving an existing employee
        Employee employee = employeeDatabase.getEmployeeById(1); // Assuming ID 1 exists
        assertNotNull(employee, "Employee should not be null for valid ID");

        // Test retrieving a non-existing employee
        Employee nonExistentEmployee = employeeDatabase.getEmployeeById(-1);
        assertNull(nonExistentEmployee, "Employee should be null for invalid ID");
    }

    @Test
    void TestDeleteEmployee(){
        // Perform the deletion
        int employeeIdToDelete = 1; // Assuming ID 1 exists and is valid for deletion
        boolean isDeleted = employeeDatabase.deleteEmployeeById(employeeIdToDelete); // Add this method in EmployeeDatabase
        assertTrue(isDeleted, "Employee should be deleted successfully");

        // Verify the employee no longer exists
        Employee employeeAfterDeletion = employeeDatabase.getEmployeeById(employeeIdToDelete);
        assertNull(employeeAfterDeletion, "Employee should not exist after deletion");
    }

    @Test
    void testIsEmailValid() {
        Employee employee = new Employee(1, "John Doe", "John", "Doe", "Manager", "HR", "john.doe@example.com", "password123",
                new java.sql.Date(System.currentTimeMillis()), 30000.00, 5000.00, "09171234567",
                "123456789", "987654321", "1122334455", "123456789", "123 Main St",
                1350.00, 1050.00, 600.00, 3500.00);

        assertTrue(employee.isEmailValid(), "Email should be valid");
    }

    @Test
    void testIsPhoneNumberValid() {
        Employee employee = new Employee(1, "John Doe", "John", "Doe", "Manager", "HR", "john.doe@example.com", "password123",
                new java.sql.Date(System.currentTimeMillis()), 30000.00, 5000.00, "09171234567",
                "123456789", "987654321", "1122334455", "123456789", "123 Main St",
                1350.00, 1050.00, 600.00, 3500.00);

        assertTrue(employee.isPhoneNumberValid(), "Phone number should be valid");
    }

}