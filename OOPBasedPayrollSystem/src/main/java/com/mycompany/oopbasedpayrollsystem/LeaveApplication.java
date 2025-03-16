/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
/**
 *
 * @author User
 */
public class LeaveApplication {
    private static final String CSV_FILE_PATH = "C:/Users/User/Downloads/Employee_Leave_Records.csv";

    public static boolean fileLeave(int employeeID, String name, String leaveType, LocalDate startDate, LocalDate endDate, String reason) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(CSV_FILE_PATH, true))) {
            String leaveRecord = employeeID + "," + name + "," + leaveType + "," + startDate + "," + endDate + "," + reason;
            writer.write(leaveRecord);
            writer.newLine();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
