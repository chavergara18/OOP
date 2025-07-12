/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopbasedpayrollsystem.service;

public class PayrollService {
    public static double calculateGrossSalary(double basic, double allowance) {
        return basic + allowance; // Ensure gross includes allowances
    }

    public static double calculateNetSalary(double basic, double allowance) {
        double gross = calculateGrossSalary(basic, allowance);
        double totalDeductions = computeSSS(gross) + computePhilHealth(gross)
                + computePagIbig(gross) + computeTax(gross);
        return gross - totalDeductions;
    }

    public static double computeSSS(double gross) {
        return Math.min(1620.00, gross * 0.045);  // SSS should use gross, not just basic salary
    }

    public static double computePhilHealth(double gross) {
        double contribution = gross * 0.025;  // Use gross salary
        return Math.min(contribution, 2250.00); 
    }

    public static double computePagIbig(double gross) {
        return Math.min(gross * 0.01, 100.00);  // Should use gross salary
    }

    public static double computeTax(double gross) {
        double annual = gross * 12; // Annual computation for tax
        double tax = 0.0;

        if (annual <= 250000) {
            tax = 0.0;
        } else if (annual <= 400000) {
            tax = (annual - 250000) * 0.20;
        } else if (annual <= 800000) {
            tax = 30000 + (annual - 400000) * 0.25;
        } else if (annual <= 2000000) {
            tax = 130000 + (annual - 800000) * 0.30;
        } else {
            tax = 490000 + (annual - 2000000) * 0.35;
        }
        return tax / 12; // Convert back to monthly
    }
}



