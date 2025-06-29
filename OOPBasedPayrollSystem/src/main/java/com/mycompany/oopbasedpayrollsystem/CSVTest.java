/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

/**
 *
 * @author User
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVTest {
        public static void main(String[] args) {
        String filePath = "C:\\Users\\User\\OneDrive\\Documents\\NetBeansProjects\\OOPBasedPayrollSystem\\MotorPH_Credentials.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
