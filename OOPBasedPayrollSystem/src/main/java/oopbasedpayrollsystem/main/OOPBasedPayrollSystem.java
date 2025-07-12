/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package oopbasedpayrollsystem.main;

/**
 *
 * @author Cha
 */
import oopbasedpayrollsystem.gui.LoginGUI;
import oopbasedpayrollsystem.model.User;

import javax.swing.*;

public class OOPBasedPayrollSystem {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI loginScreen = new LoginGUI();
            loginScreen.setVisible(true);
        });
    }

    public static void handleLogin(String username, String password) {
        try {
            User user = LoginSystem.login(username, password);

            if (user != null) {
                JOptionPane.showMessageDialog(null, "✅ Login Successful! Welcome " + user.getFullName());
                user.openDashboard(); // Polymorphism
            } else {
                JOptionPane.showMessageDialog(null, "❌ Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Login Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }
}
