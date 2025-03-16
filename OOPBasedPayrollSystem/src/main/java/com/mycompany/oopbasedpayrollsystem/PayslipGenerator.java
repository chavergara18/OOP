/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

/**
 *
 * @author User
 */
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class PayslipGenerator {

    public static void generatePayslip(Employee employee) {
        // Retrieve salary details from employee object
        double basicSalary = employee.getGrossSalary(); //  Ensure correct salary retrieval
        double allowance = employee.getAllowance(); //  Retrieve allowance

        // Compute salary components
        double grossSalary = PayrollCalculator.calculateGrossSalary(basicSalary, allowance);
        double sssDeduction = PayrollCalculator.computeSSS(grossSalary);
        double philHealthDeduction = PayrollCalculator.computePhilHealth(grossSalary);
        double pagIbigDeduction = PayrollCalculator.computePagIbig(grossSalary); //  Now passing salary
        double taxDeduction = PayrollCalculator.computeTax(grossSalary);
        double totalDeductions = sssDeduction + philHealthDeduction + pagIbigDeduction + taxDeduction;
        double netSalary = grossSalary - totalDeductions;

        // Print payslip to console
        System.out.println("\n=========================================");
        System.out.println("          MOTORPH PAYSLIP");
        System.out.println("=========================================");
        System.out.println("Date: " + LocalDate.now());
        System.out.println("Employee ID: " + employee.getEmployeeID());
        System.out.println("Name: " + employee.getFirstName() + " " + employee.getSurname());
        System.out.println("Position: " + employee.getPosition());
        System.out.println("-----------------------------------------");
        System.out.printf("Basic Salary:   PHP %,10.2f%n", basicSalary);
        System.out.printf("Allowance:      PHP %,10.2f%n", allowance);
        System.out.printf("Gross Salary:   PHP %,10.2f%n", grossSalary);
        System.out.println("-----------------------------------------");
        System.out.println("Deductions:");
        System.out.printf("SSS:           PHP %,10.2f%n", sssDeduction);
        System.out.printf("PhilHealth:    PHP %,10.2f%n", philHealthDeduction);
        System.out.printf("Pag-IBIG:      PHP %,10.2f%n", pagIbigDeduction);
        System.out.printf("Tax:           PHP %,10.2f%n", taxDeduction);
        System.out.println("-----------------------------------------");
        System.out.printf("Total Deductions: PHP %,10.2f%n", totalDeductions);
        System.out.printf("Net Salary:       PHP %,10.2f%n", netSalary);
        System.out.println("=========================================\n");

        // Prepare payslip for GUI
        String payslip = String.format(
                "=========================================\n" +
                "          MOTORPH PAYSLIP\n" +
                "=========================================\n" +
                "Date: %s\n" +
                "Employee ID: %s\n" +
                "Name: %s %s\n" +
                "Position: %s\n" +
                "-----------------------------------------\n" +
                "Basic Salary:   PHP %,10.2f\n" +
                "Allowance:      PHP %,10.2f\n" +
                "Gross Salary:   PHP %,10.2f\n" +
                "-----------------------------------------\n" +
                "Deductions:\n" +
                "SSS:           PHP %,10.2f\n" +
                "PhilHealth:    PHP %,10.2f\n" +
                "Pag-IBIG:      PHP %,10.2f\n" +
                "Tax:           PHP %,10.2f\n" +
                "-----------------------------------------\n" +
                "Total Deductions: PHP %,10.2f\n" +
                "Net Salary:       PHP %,10.2f\n" +
                "=========================================",
                LocalDate.now(), 
                employee.getEmployeeID(), 
                employee.getFirstName(), employee.getSurname(), 
                employee.getPosition(),
                basicSalary, allowance, grossSalary,
                sssDeduction, philHealthDeduction, pagIbigDeduction, taxDeduction,
                totalDeductions, netSalary
        );

        // Display in GUI
        JOptionPane.showMessageDialog(null, payslip, "Payslip", JOptionPane.INFORMATION_MESSAGE);
    }
}
