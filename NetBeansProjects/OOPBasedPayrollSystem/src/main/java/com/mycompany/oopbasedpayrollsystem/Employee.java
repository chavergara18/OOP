/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.time.LocalDate;

public class Employee extends User {
    private double basicSalary;
    private double allowance;

    public Employee(int employeeID, String firstName, String surname, LocalDate birthday, String address,
                    String phoneNumber, String sssNumber, String philHealthNumber, String tinNumber,
                    String pagIbigNumber, String status, String position,
                    double basicSalary, double allowance, String username, String password) {
        super(employeeID, firstName, surname, birthday, address, phoneNumber, sssNumber, philHealthNumber,
              tinNumber, pagIbigNumber, status, position, basicSalary, allowance, username, password);
        this.basicSalary = basicSalary;
        this.allowance = allowance;
    }

    public double getNetSalary() {
        return PayrollCalculator.calculateNetSalary(basicSalary, allowance);
    }

    public double getGrossSalary() {
        return PayrollCalculator.calculateGrossSalary(basicSalary, allowance);
    }

    public double getSSS() {
        return PayrollCalculator.computeSSS(basicSalary);
    }

    public double getPhilHealth() {
        return PayrollCalculator.computePhilHealth(basicSalary);
    }

    public double getPagIbig() {
        return PayrollCalculator.computePagIbig(basicSalary);
    }

    public double getTax() {
        return PayrollCalculator.computeTax(basicSalary);
    }

    public String getPagIbigNumber() {
        return super.getPagIbigNumber();
    }
}
