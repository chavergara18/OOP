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
    private String credentialsFilePath;

    public EmployeeDatabase(String credentialsFilePath) {
        this.credentialsFilePath = credentialsFilePath;
    }

    public int validateLogin(String username, String password) {
        System.out.println("🔍 Checking Credentials File: " + credentialsFilePath);

        try (BufferedReader br = new BufferedReader(new FileReader(credentialsFilePath))) {
            String line;
            String header = br.readLine(); 
            System.out.println("🔹 CSV Header: " + header);  

            while ((line = br.readLine()) != null) {
                String[] data = parseCSVLine(line);

                if (data.length < 3) {  // Ensure at least Employee ID, Username, and Password exist
                    System.out.println("⚠ Skipping Invalid Row: " + Arrays.toString(data));
                    continue;
                }

                // Extract data safely
                String storedEmployeeID = data[0].trim();
                String storedUsername = data[1].trim().replace("\"", ""); 
                String storedPassword = data[2].trim().replace("\"", ""); 

                // Debugging info
                System.out.println("📌 Checking User: [" + storedUsername + "] | Password: [" + storedPassword + "]");

                // Validate credentials
                if (storedUsername.equalsIgnoreCase(username.trim()) && storedPassword.equals(password.trim())) {
                    System.out.println("✅ LOGIN SUCCESS: " + storedUsername);
                    return Integer.parseInt(storedEmployeeID); 
                }
            }
        } catch (IOException e) {
            System.out.println("❌ ERROR: Could not read CSV file. Make sure it exists at: " + credentialsFilePath);
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println("❌ ERROR: Invalid Employee ID format in CSV. Please check the data.");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("❌ ERROR: Unexpected error occurred.");
            e.printStackTrace();
        }

        System.out.println("❌ LOGIN FAILED: Invalid credentials");
        return -1;
    }

    private String[] parseCSVLine(String line) {
        return line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Handles quoted values properly
    }
}

















