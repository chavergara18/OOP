/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

public class PayrollCalculator {
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
        // Calculate annual gross salary
        double annual = gross * 12;
        double tax = 0.0;

        // Apply tax brackets
        if (annual <= 250000) {
            tax = 0.0; // No tax for annual income <= 250,000
        } else if (annual <= 400000) {
            tax = (annual - 250000) * 0.20; // 20% tax for income between 250,001 and 400,000
        } else if (annual <= 800000) {
            tax = 30000 + (annual - 400000) * 0.25; // 25% tax for income between 400,001 and 800,000
        } else if (annual <= 2000000) {
            tax = 130000 + (annual - 800000) * 0.30; // 30% tax for income between 800,001 and 2,000,000
        } else {
            tax = 490000 + (annual - 2000000) * 0.35; // 35% tax for income above 2,000,000
        }

        // Convert annual tax to monthly tax
        return Math.round((tax / 12) * 100.0) / 100.0;
    }
}



