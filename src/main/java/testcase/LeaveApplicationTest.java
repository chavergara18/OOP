package testcase;

import com.mycompany.oopbasedpayrollsystem.LeaveApplication;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class LeaveApplicationTest {

    @Test
    void testFileLeave() {
        // Test valid leave application
        boolean result = LeaveApplication.fileLeave(
                1, // Employee ID
                "Vacation", // Leave type
                LocalDate.of(2023, 10, 1), // Start date
                LocalDate.of(2023, 10, 5), // End date
                "Pending" // Status
        );
        assertTrue(result, "Leave application should be filed successfully");


    }
}