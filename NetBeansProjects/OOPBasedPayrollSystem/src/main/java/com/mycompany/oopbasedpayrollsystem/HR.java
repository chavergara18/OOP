/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.time.LocalDate;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class HR extends User implements UserActions {
    private static final String CSV_FILE_PATH = "C:\\Users\\User\\Downloads\\MotorPH Employee Data - Employee Details.csv";

    public HR() {
        super(0, "", "", LocalDate.now(), "", "", "", "", "", "", "", "", 0.0, 0.0, "", "");
    }

    public HR(int employeeID, String firstName, String surname, LocalDate birthday, String address,
              String phoneNumber, String sssNumber, String philHealthNumber, String tinNumber,
              String pagIbigNumber, String status, String position, double basicSalary, double allowance,
              String username, String password) {
        super(employeeID, firstName, surname, birthday, address, phoneNumber, sssNumber, philHealthNumber,
              tinNumber, pagIbigNumber, status, position, basicSalary, allowance, username, password);
    }

    @Override
    public boolean login(String username, String password) {
        EmployeeDatabase db = new EmployeeDatabase(CSV_FILE_PATH);
        int empID = db.validateLogin(username, password);
        if (empID != -1) {
            System.out.println("✅ HR Login Successful: " + username);
            return true;
        } else {
            System.out.println("❌ HR Login Failed: Invalid Credentials");
            return false;
        }
    }

    @Override
    public String[] viewPersonalInformation() {
        return new String[]{
            String.valueOf(getEmployeeID()), getFirstName(), getSurname(),
            getBirthday().toString(), getAddress(), getPhoneNumber(),
            getSssNumber(), getPhilHealthNumber(), getTinNumber(),
            getPagIbigNumber(), getStatus(), getPosition()
        };
    }

    @Override
    public String[] viewSalary() {
        double grossSalary = PayrollCalculator.calculateGrossSalary(getBasicSalary(), getAllowance());
        double netSalary = PayrollCalculator.calculateNetSalary(getBasicSalary(), getAllowance());
        return new String[]{String.valueOf(grossSalary), String.valueOf(netSalary)};
    }

    public boolean addEmployee(Employee employee) {
        EmployeeDatabase db = new EmployeeDatabase(CSV_FILE_PATH);
        String[] employeeData = new String[]{
            String.valueOf(employee.getEmployeeID()), employee.getSurname(),
            employee.getFirstName(), employee.getBirthday().toString(),
            employee.getAddress(), employee.getPhoneNumber(), employee.getSssNumber(),
            employee.getPhilHealthNumber(), employee.getTinNumber(), employee.getPagIbigNumber(),
            employee.getStatus(), employee.getPosition(),
            String.valueOf(employee.getBasicSalary()), String.valueOf(employee.getAllowance()),
            employee.getUsername(), employee.getPassword()
        };

        System.out.println("✅ Adding Employee: " + employee.getFirstName() + " " + employee.getSurname());
        boolean result = db.addEmployee(employeeData);
        if (result) {
            System.out.println("✅ Employee Added Successfully!");
        } else {
            System.out.println("❌ Failed to Add Employee!");
        }
        return result;
    }

    public boolean deleteEmployee(int employeeID) {
        EmployeeDatabase db = new EmployeeDatabase(CSV_FILE_PATH);
        System.out.println("❌ Deleting Employee ID: " + employeeID);
        boolean result = db.deleteEmployee(employeeID);
        if (result) {
            System.out.println("✅ Employee Deleted Successfully!");
        } else {
            System.out.println("❌ Failed to Delete Employee!");
        }
        return result;
    }
}





