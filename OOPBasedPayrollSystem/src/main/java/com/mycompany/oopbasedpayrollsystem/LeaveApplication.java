/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class LeaveApplication {
    private static final String CSV_FILE_PATH;

    static {
        // ✅ Use dynamic file path
        String BASE_PATH = System.getProperty("user.dir"); // Gets project root directory
        CSV_FILE_PATH = BASE_PATH + File.separator + "src" + File.separator + "Employee_Leave_Records.csv";
    }

    public static boolean fileLeave(int employeeID, String name, String leaveType, LocalDate startDate, LocalDate endDate, String reason) {
        try {
            File file = new File(CSV_FILE_PATH);
            
            // ✅ Create file if it doesn't exist
            boolean isNewFile = file.createNewFile();

            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
                // ✅ Write headers only if the file is new
                if (isNewFile) {
                    writer.write("EmployeeID,Name,LeaveType,StartDate,EndDate,Reason");
                    writer.newLine();
                }

                String leaveRecord = employeeID + "," + name + "," + leaveType + "," + startDate + "," + endDate + "," + reason;
                writer.write(leaveRecord);
                writer.newLine();

                System.out.println("✅ Leave application recorded for " + name);
                return true;
            }
        } catch (IOException e) {
            System.out.println("❌ ERROR: Could not save leave application.");
            e.printStackTrace();
            return false;
        }
    }
}

