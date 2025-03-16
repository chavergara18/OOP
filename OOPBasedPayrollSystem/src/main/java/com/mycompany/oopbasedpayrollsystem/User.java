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

public class User {
    private int employeeID;
    private String firstName;
    private String surname;
    private LocalDate birthday;
    private String address;
    private String phoneNumber;
    private String sssNumber;
    private String philHealthNumber;
    private String tinNumber;
    private String pagIbigNumber; // 🔥 ADDED
    private String status; // 🔥 ADDED
    private String position;
    private double basicSalary;
    private double allowance;
    private String username;
    private String password;

    // 🔥 FIXED CONSTRUCTOR TO MATCH Employee.java
    public User(int employeeID, String firstName, String surname, LocalDate birthday, String address,
                String phoneNumber, String sssNumber, String philHealthNumber, String tinNumber,
                String pagIbigNumber, String status, String position, double basicSalary, double allowance,
                String username, String password) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.surname = surname;
        this.birthday = birthday;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.sssNumber = sssNumber;
        this.philHealthNumber = philHealthNumber;
        this.tinNumber = tinNumber;
        this.pagIbigNumber = pagIbigNumber;
        this.status = status;
        this.position = position;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.username = username;
        this.password = password;
    }

    // ✅ Getters
    public int getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getSurname() { return surname; }
    public LocalDate getBirthday() { return birthday; }
    public String getAddress() { return address; }
    public String getPhoneNumber() { return phoneNumber; } // 🔥 ADDED
    public String getSssNumber() { return sssNumber; }
    public String getPhilHealthNumber() { return philHealthNumber; }
    public String getTinNumber() { return tinNumber; }
    public String getPagIbigNumber() { return pagIbigNumber; } // 🔥 ADDED
    public String getStatus() { return status; } // 🔥 ADDED
    public String getPosition() { return position; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowance() { return allowance; }
    public String getUsername() { return username; }
    public String getPassword() { return password; }
}
