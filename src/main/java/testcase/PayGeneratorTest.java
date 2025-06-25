package testcase;

import com.mycompany.oopbasedpayrollsystem.Employee;
import com.mycompany.oopbasedpayrollsystem.PayslipGenerator;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.sql.Date;

import static org.junit.jupiter.api.Assertions.*;

class PayslipGeneratorTest {
    
    public static void main ( String [] args){

        PayslipGeneratorTest test = new PayslipGeneratorTest();
        test.testGeneratePayslip();
    }

    @Test
    void testGeneratePayslip() {
        // Create Employee object using constructor
        Employee mockEmployee = new Employee(
                12345, "John Doe", "John", "Doe", "Software Engineer", "IT", "john.doe@example.com", "password123",
                Date.valueOf("2020-01-01"), 50000.00, 10000.00, "09123456789",
                "SSS12345", "PH12345", "PAGIBIG12345", "TIN12345", "123 Main St",
                1500.00, 750.00, 500.00, 5000.00
        );

        // Generate payslip
        PayslipGenerator.generatePayslip(mockEmployee);

        // Verify the file was created
        String expectedFilename = "Payslip_12345.pdf";
        File generatedFile = new File(expectedFilename);
        assertTrue(generatedFile.exists(), "Payslip file should be generated");

        // Clean up the generated file
        if (generatedFile.exists()) {
            generatedFile.delete();
        }
    }
}