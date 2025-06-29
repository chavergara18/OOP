/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

public class Credential {
    private int employeeID;
    private String username;
    private String password;

    public Credential(int employeeID, String username, String password) {
        this.employeeID = employeeID;
        this.username = username;
        this.password = password;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}



