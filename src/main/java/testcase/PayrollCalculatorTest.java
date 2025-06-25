package testcase;

import com.mycompany.oopbasedpayrollsystem.PayrollCalculator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PayrollCalculatorTest {

    @Test
    void testCalculateGrossSalary() {
        double basic = 30000.00;
        double allowance = 5000.00;
        double grossSalary = PayrollCalculator.calculateGrossSalary(basic, allowance);
        assertEquals(35000.00, grossSalary, 0.01, "Gross salary calculation is incorrect");
    }

    @Test
    void testCalculateNetSalary() {
        double basic = 30000.00;
        double allowance = 5000.00;
        double netSalary = PayrollCalculator.calculateNetSalary(basic, allowance);
        assertTrue(netSalary > 0, "Net salary should be greater than zero");
    }

    @Test
    void testComputeSSS() {
        double gross = 35000.00;
        double sssDeduction = PayrollCalculator.computeSSS(gross);
        assertEquals(1575.00, sssDeduction, 0.01, "SSS deduction calculation is incorrect");
    }

    @Test
    void testComputePhilHealth() {
        double gross = 35000.00;
        double philHealthDeduction = PayrollCalculator.computePhilHealth(gross);
        assertEquals(875.00, philHealthDeduction, 0.01, "PhilHealth deduction calculation is incorrect");
    }

    @Test
    void testComputePagIbig() {
        double gross = 35000.00;
        double pagIbigDeduction = PayrollCalculator.computePagIbig(gross);
        assertEquals(100.00, pagIbigDeduction, 0.01, "PagIbig deduction calculation is incorrect");
    }

    @Test
    void testComputeTax() {
        double gross = 35000.00;
        double taxDeduction = PayrollCalculator.computeTax(gross);
        assertEquals(2916.67, taxDeduction, 0.01, "Tax deduction calculation is incorrect");
    }
}