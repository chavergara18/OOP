/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopbasedpayrollsystem.main;

/**
 *
 * @author Cha
 */
import oopbasedpayrollsystem.gui.LoginGUI;
import oopbasedpayrollsystem.model.User;
import oopbasedpayrollsystem.model.AdminUser;
import oopbasedpayrollsystem.model.EmployeeUser;
import oopbasedpayrollsystem.util.DatabaseConnection;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class LoginSystem {

    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator;

    public static User login(String username, String password) {
        // 1. Try admin login
        if (validateAdminLogin(username, password)) {
            return new AdminUser(
                0, "Admin", "User", LocalDate.of(1990, 1, 1), // dummy data
                "Admin Address", "09123456789", "SSS001", "PH001",
                "TIN001", "PAGIBIG001", "Active", "Admin",
                username, password
            );
        }

        // 2. Try employee login
        String query = "SELECT * FROM employees WHERE email = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    int id = rs.getInt("id");
                    String firstName = rs.getString("first_name");
                    String lastName = rs.getString("last_name");
                    LocalDate birthday = rs.getDate("birthday").toLocalDate();
                    String address = rs.getString("address");
                    String phone = rs.getString("phone_number");
                    String sss = rs.getString("sss_number");
                    String philHealth = rs.getString("philhealth_number");
                    String tin = rs.getString("tin_number");
                    String pagIbig = rs.getString("pagibig_number");
                    String status = rs.getString("status");
                    String position = rs.getString("position");
                    String email = rs.getString("email");

                    return new EmployeeUser(
                        id, firstName, lastName, birthday,
                        address, phone, sss, philHealth, tin, pagIbig,
                        status, position, email, password
                    );
                }
            }

        } catch (SQLException e) {
            System.out.println("❌ DB Error during login: " + e.getMessage());
        }

        return null; // login failed
    }

    public static boolean validateAdminLogin(String username, String password) {
        String query = "SELECT * FROM admin_credentials WHERE username = ? AND password = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                return rs.next(); // true if admin login is valid
            }

        } catch (SQLException e) {
            System.out.println("❌ Login DB Error: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            LoginGUI loginScreen = new LoginGUI();
            loginScreen.setVisible(true);
        });
    }
}









