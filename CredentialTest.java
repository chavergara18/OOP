package com.mycompany.oopbasedpayrollsystem;

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
public class CredentialTest {
    
    private Credential instance;

    @BeforeAll
    public static void setUpClass() {
        // This method runs once before all tests in the class.
        System.out.println("Setting up CredentialTest class...");
    }
    
    @AfterAll
    public static void tearDownClass() {
        // This method runs once after all tests in the class have finished.
        System.out.println("Tearing down CredentialTest class...");
    }
    
    @BeforeEach
    public void setUp() {
        // This method runs before each test method.
        // Create a new Credential instance with test data
        instance = new Credential(1, "testUser ", "testPassword");
    }
    
    @AfterEach
    public void tearDown() {
        // This method runs after each test method.
        instance = null; // Clean up the instance
    }

    @Test
    public void testGetEmployeeID() {
        System.out.println("getEmployeeID");
        int expResult = 1; // Expected employee ID
        int result = instance.getEmployeeID();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUsername() {
        System.out.println("getUsername");
        String expResult = "testUser "; // Expected username
        String result = instance.getUsername();
        assertEquals(expResult, result);
    }

    @Test
    public void testGetPassword() {
        System.out.println("getPassword");
        String expResult = "testPassword"; // Expected password
        String result = instance.getPassword();
        assertEquals(expResult, result);
    }
}
