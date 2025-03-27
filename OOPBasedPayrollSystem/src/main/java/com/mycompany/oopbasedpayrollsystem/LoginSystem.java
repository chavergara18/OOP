/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginSystem {
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator;
    private static final String ATTENDANCE_CSV = BASE_PATH + "attendance.csv"; 

    private EmployeeDatabase employeeDatabase;

    public LoginSystem() {
        this.employeeDatabase = new EmployeeDatabase();
    }

    public int performLogin(String username, String password) {
        return employeeDatabase.validateLogin(username, password);
    }

    public void recordAttendance(int employeeID, String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        File file = new File(ATTENDANCE_CSV);
        boolean fileExists = file.exists();

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            if (!fileExists) {
                writer.write("EmployeeID,Action,Timestamp");
                writer.newLine();
            }

            writer.write(employeeID + "," + action + "," + now.format(formatter));
            writer.newLine();
            System.out.println("📌 Attendance Recorded: " + employeeID + " - " + action);
        } catch (IOException e) {
            System.out.println("❌ Error recording attendance: " + e.getMessage());
        }
    }
}









