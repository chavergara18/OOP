/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

/**
 *
 * @author User
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class LoginSystem {
    private static final String ATTENDANCE_CSV = "C:/Users/User/Downloads/attendance.csv";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        // Pass the correct CSV file path to EmployeeDatabase
        String csvFilePath = "C:/Users/User/Downloads/MotorPH Employee Data - Employee Details.csv";
        EmployeeDatabase db = new EmployeeDatabase(csvFilePath);

        int empID = db.validateLogin(username, password);

        if (empID != -1) {
            System.out.println("✅ Login Successful! Employee ID: " + empID);
            
            // Log "Time In" when employee logs in
            recordAttendance(String.valueOf(empID), "Time In");
            
            // Simulate logout after pressing Enter (for testing)
            System.out.println("\nPress Enter to log out...");
            scanner.nextLine();
            
            // Log "Time Out" when employee logs out
            recordAttendance(String.valueOf(empID), "Time Out");

            System.out.println("✅ Logout Successful!");
        } else {
            System.out.println("❌ Invalid credentials. Please try again.");
        }

        scanner.close();
    }

    // ✅ Method to record attendance
    private static void recordAttendance(String employeeID, String action) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ATTENDANCE_CSV, true))) {
            writer.write(employeeID + "," + action + "," + now.format(formatter));
            writer.newLine();
            System.out.println("📌 Attendance Recorded: " + employeeID + " - " + action);
        } catch (IOException e) {
            System.out.println("❌ Error recording attendance: " + e.getMessage());
        }
    }
}


