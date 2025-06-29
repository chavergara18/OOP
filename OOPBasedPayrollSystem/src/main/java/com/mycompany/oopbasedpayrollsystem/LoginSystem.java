/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginSystem {
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator;

    private EmployeeDatabase employeeDatabase;
    
    public LoginSystem() {
        this.employeeDatabase = new EmployeeDatabase();
    }
    
    public static boolean validateAdminLogin(String username, String password) {
        String query = "SELECT * FROM admin_credentials WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // true if login is valid
            }

        } catch (SQLException e) {
            System.out.println("❌ Login DB Error: " + e.getMessage());
            return false;
        }
    }
    
    public static int validateEmployeeLogin(String email, String password) {
    String query = "SELECT id FROM employees WHERE email = ? AND password = ?";
    System.out.println("🔍 Trying employee login: " + email + " / " + password);

    try (Connection conn = DatabaseConnection.getConnection();
         PreparedStatement stmt = conn.prepareStatement(query)) {

        stmt.setString(1, email);
        stmt.setString(2, password);

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.out.println("✅ Employee found, ID: " + rs.getInt("id"));
                return rs.getInt("id");
            } else {
                System.out.println("❌ No match found in DB.");
            }
        }

    } catch (SQLException e) {
        System.out.println("❌ Employee Login Error: " + e.getMessage());
    }

    return -1;
}
   
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginGUI loginScreen = new LoginGUI();
            loginScreen.setVisible(true);
        });
    }
}









