/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class EmployeeDatabase {
    private String csvFilePath;

    public EmployeeDatabase(String csvFilePath) {
        this.csvFilePath = csvFilePath;
    }

    public int validateLogin(String username, String password) {
        System.out.println("🔍 Checking CSV File: " + csvFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;
            String header = br.readLine(); 
            System.out.println("🔹 CSV Header: " + header);  

            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);

                
                if (data.length < 16) {
                    System.out.println("⚠ Skipping Invalid Row: " + Arrays.toString(data));
                    continue;
                }

                
                int usernameIndex = data.length - 2; // Second last column
                int passwordIndex = data.length - 1; // Last column

                String storedUsername = data[usernameIndex].trim().replaceAll("\"", "");
                String storedPassword = data[passwordIndex].trim().replaceAll("\"", "");

                
                System.out.println("📌 Found User: [" + storedUsername + "] | Password: [" + storedPassword + "]");

                if (storedUsername.equalsIgnoreCase(username.trim()) && storedPassword.equals(password.trim())) {
                    System.out.println("✅ LOGIN SUCCESS: " + storedUsername);
                    return Integer.parseInt(data[0].trim()); 
                }
            }
        } catch (IOException e) {
            System.out.println("❌ ERROR: Could not read CSV file.");
            e.printStackTrace();
        }

        System.out.println("❌ LOGIN FAILED: Invalid credentials");
        return -1;
    }

    private String[] parseCSVLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); 
    }
}















