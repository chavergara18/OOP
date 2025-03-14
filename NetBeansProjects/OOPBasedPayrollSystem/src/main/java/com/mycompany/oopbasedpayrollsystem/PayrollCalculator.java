/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

/**
 *
 * @author User
 */
public class PayrollCalculator {
    private static final double SSS_MAX = 1350.0;
    private static final double PHILHEALTH_MAX = 5000.0 * 0.025; // 2.5% of 5000
    private static final double PAGIBIG_MAX = 100.0;

    public static double calculateGrossSalary(double basicSalary, double allowance) {
        return basicSalary + allowance;
    }

    public static double computeSSS(double salary) {
        double sssContribution = salary * 0.045; // 4.5% of salary
        return Math.min(sssContribution, SSS_MAX);
    }

    public static double computePhilHealth(double salary) {
        double philHealth = salary * 0.025; // 2.5% employee share
        return Math.min(philHealth, PHILHEALTH_MAX);
    }

    public static double computePagIbig(double salary) {
        return (salary < 1500) ? (salary * 0.01) : (salary * 0.02);
    }

    public static double computeTax(double salary) {
        double tax = 0.0;
        if (salary > 250000) {
            tax = (salary - 250000) * 0.20; // Example for incomes > ₱250,000
        }
        return tax;
    }

    public static double calculateNetSalary(double basicSalary, double allowance) {
        double grossSalary = calculateGrossSalary(basicSalary, allowance);
        double sss = computeSSS(grossSalary);
        double philHealth = computePhilHealth(grossSalary);
        double pagIbig = computePagIbig(grossSalary);
        double tax = computeTax(grossSalary);

        return grossSalary - (sss + philHealth + pagIbig + tax);
    }
}
