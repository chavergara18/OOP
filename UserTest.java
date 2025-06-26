package com.mycompany.oopbasedpayrollsystem;

import java.time.LocalDate;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author Sherlyn Pequit
 */
public class UserTest {

    // Declare a User instance that can be used across multiple tests
    private User testUser;

    public UserTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        // This method runs once before all tests in the class.
        // Useful for setting up heavy resources like database connections (if not using in-memory DB for tests).
        System.out.println("Setting up UserTest class...");
    }

    @AfterAll
    public static void tearDownClass() {
        // This method runs once after all tests in the class have finished.
        // Useful for cleaning up heavy resources.
        System.out.println("Tearing down UserTest class...");
    }

    @BeforeEach
    public void setUp() {
        // This method runs before each test method.
        // It's ideal for initializing objects that each test needs in a fresh state.
        System.out.println("Setting up test case...");
        testUser = new User(
                1001,
                "John",
                "Doe",
                LocalDate.of(1990, 5, 15),
                "123 Main St, Anytown",
                "09123456789",
                "SSS12345",
                "PHL67890",
                "TIN11223",
                "PAGIBIG44556",
                "Regular",
                "Software Engineer",
                "john.doe@example.com",
                "securepassword123"
        );
    }

    @AfterEach
    public void tearDown() {
        // This method runs after each test method.
        // Useful for cleaning up resources used by a single test.
        System.out.println("Tearing down test case...");
        testUser = null; // Clear the instance after each test
    }

    /**
     * Test of getEmployeeID method, of class User.
     */
    @Test
    public void testGetEmployeeID() {
        System.out.println("Testing getEmployeeID");
        int expResult = 1001;
        int result = testUser.getEmployeeID();
        assertEquals(expResult, result, "Employee ID should match the initialized value.");
    }

    /**
     * Test of getFirstName method, of class User.
     */
    @Test
    public void testGetFirstName() {
        System.out.println("Testing getFirstName");
        String expResult = "John";
        String result = testUser.getFirstName();
        assertEquals(expResult, result, "First name should match the initialized value.");
    }

    /**
     * Test of getLastName method, of class User.
     */
    @Test
    public void testGetLastName() {
        System.out.println("Testing getLastName");
        String expResult = "Doe";
        String result = testUser.getLastName();
        assertEquals(expResult, result, "Last name should match the initialized value.");
    }

    /**
     * Test of getFullName method, of class User.
     */
    @Test
    public void testGetFullName() {
        System.out.println("Testing getFullName");
        String expResult = "John Doe";
        String result = testUser.getFullName();
        assertEquals(expResult, result, "Full name should be a concatenation of first and last names.");
    }

    /**
     * Test of getBirthday method, of class User.
     */
    @Test
    public void testGetBirthday() {
        System.out.println("Testing getBirthday");
        LocalDate expResult = LocalDate.of(1990, 5, 15);
        LocalDate result = testUser.getBirthday();
        assertEquals(expResult, result, "Birthday should match the initialized value.");
    }

    /**
     * Test of getAddress method, of class User.
     */
    @Test
    public void testGetAddress() {
        System.out.println("Testing getAddress");
        String expResult = "123 Main St, Anytown";
        String result = testUser.getAddress();
        assertEquals(expResult, result, "Address should match the initialized value.");
    }

    /**
     * Test of getPhoneNumber method, of class User.
     */
    @Test
    public void testGetPhoneNumber() {
        System.out.println("Testing getPhoneNumber");
        String expResult = "09123456789";
        String result = testUser.getPhoneNumber();
        assertEquals(expResult, result, "Phone number should match the initialized value.");
    }

    /**
     * Test of getSssNumber method, of class User.
     */
    @Test
    public void testGetSssNumber() {
        System.out.println("Testing getSssNumber");
        String expResult = "SSS12345";
        String result = testUser.getSssNumber();
        assertEquals(expResult, result, "SSS number should match the initialized value.");
    }

    /**
     * Test of getPhilHealthNumber method, of class User.
     */
    @Test
    public void testGetPhilHealthNumber() {
        System.out.println("Testing getPhilHealthNumber");
        String expResult = "PHL67890";
        String result = testUser.getPhilHealthNumber();
        assertEquals(expResult, result, "PhilHealth number should match the initialized value.");
    }

    /**
     * Test of getTinNumber method, of class User.
     */
    @Test
    public void testGetTinNumber() {
        System.out.println("Testing getTinNumber");
        String expResult = "TIN11223";
        String result = testUser.getTinNumber();
        assertEquals(expResult, result, "TIN number should match the initialized value.");
    }

    /**
     * Test of getPagIbigNumber method, of class User.
     */
    @Test
    public void testGetPagIbigNumber() {
        System.out.println("Testing getPagIbigNumber");
        String expResult = "PAGIBIG44556";
        String result = testUser.getPagIbigNumber();
        assertEquals(expResult, result, "Pag-Ibig number should match the initialized value.");
    }

    /**
     * Test of getStatus method, of class User.
     */
    @Test
    public void testGetStatus() {
        System.out.println("Testing getStatus");
        String expResult = "Regular";
        String result = testUser.getStatus();
        assertEquals(expResult, result, "Status should match the initialized value.");
    }

    /**
     * Test of getPosition method, of class User.
     */
    @Test
    public void testGetPosition() {
        System.out.println("Testing getPosition");
        String expResult = "Software Engineer";
        String result = testUser.getPosition();
        assertEquals(expResult, result, "Position should match the initialized value.");
    }

    /**
     * Test of getUsername method, of class User.
     */
    @Test
    public void testGetUsername() {
        System.out.println("Testing getUsername");
        String expResult = "john.doe@example.com";
        String result = testUser.getUsername();
        assertEquals(expResult, result, "Username should match the initialized value.");
    }

    /**
     * Test of getPassword method, of class User.
     */
    @Test
    public void testGetPassword() {
        System.out.println("Testing getPassword");
        String expResult = "securepassword123";
        String result = testUser.getPassword();
        assertEquals(expResult, result, "Password should match the initialized value.");
    }

    /**
     * Test of verifyPassword method, of class User.
     */
    @Test
    public void testVerifyPassword() {
        System.out.println("Testing verifyPassword");

        // Test with correct password
        String correctPassword = "securepassword123";
        assertTrue(testUser.verifyPassword(correctPassword), "Should return true for correct password.");

        // Test with incorrect password
        String incorrectPassword = "wrongpassword";
        assertFalse(testUser.verifyPassword(incorrectPassword), "Should return false for incorrect password.");

        // Test with empty password
        String emptyPassword = "";
        assertFalse(testUser.verifyPassword(emptyPassword), "Should return false for empty password if actual password is not empty.");
    }
}
