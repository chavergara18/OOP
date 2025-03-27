/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.time.LocalDate;

public class Employee {
    private int employeeID;
    private String firstName;
    private String lastName;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philHealthNumber;
    private String tinNumber;
    private String pagIbigNumber;
    private String employmentStatus;
    private String position;
    private double basicSalary;
    private double allowance;  // ✅ Explicitly added allowance field
    private double sssDeduction;
    private double philHealthDeduction;
    private double pagibigDeduction;
    private double birTax;
    private String username;
    private String password;

    // ✅ Updated Constructor to Include Allowance and Deductions
    public Employee(int employeeID, String firstName, String lastName, LocalDate birthday, 
                    String address, String phoneNumber, String sssNumber, String philHealthNumber, 
                    String tinNumber, String pagIbigNumber, String employmentStatus, String position, 
                    double basicSalary, double allowance, double sssDeduction, double philHealthDeduction,
                    double pagibigDeduction, double birTax, String username, String password) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tinNumber = tinNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.employmentStatus = employmentStatus;
        this.position = position;
        this.basicSalary = basicSalary;
        this.allowance = allowance;  // ✅ Assigned allowance correctly
        this.sssDeduction = sssDeduction;
        this.philHealthDeduction = philHealthDeduction;
        this.pagibigDeduction = pagibigDeduction;
        this.birTax = birTax;
        this.username = username;
        setPassword(password);
    }

    // ✅ Calculate Net Salary Correctly
    public double calculateNetSalary() {
        double grossSalary = basicSalary + allowance;  // ✅ Now using `allowance` correctly
        double totalDeductions = sssDeduction + philHealthDeduction + pagibigDeduction + birTax;
        return grossSalary - totalDeductions;
    }

    // ✅ Minimal Constructor (For Login)
    public Employee(int employeeID, String username, String password) {
        this.employeeID = employeeID;
        this.username = username;
        setPassword(password);
    }

    // ✅ Getters for Payslip & Payroll
    public int getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }

    public String getFullName() {
        return (firstName != null ? firstName : "") + " " + (lastName != null ? lastName : "");
    }

    public String getPosition() { return position; }
    public String getEmploymentStatus() { return employmentStatus; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowance() { return allowance; }  // ✅ Fixed getter to use `allowance`

    // ✅ New Getters for Deductions
    public double getSssDeduction() { return sssDeduction; }
    public double getPhilHealthDeduction() { return philHealthDeduction; }
    public double getPagibigDeduction() { return pagibigDeduction; }
    public double getBirTax() { return birTax; }

    // ✅ Secure password handling (optional future encryption)
    public String getUsername() { return username; }

    public void setPassword(String password) {
        this.password = password; 
    }

    public boolean checkPassword(String inputPassword) {
        return this.password.equals(inputPassword);
    }
}





