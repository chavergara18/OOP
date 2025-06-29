/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.oopbasedpayrollsystem;

import javax.swing.*;

public class OOPBasedPayrollSystem {

    private static final String EMPLOYEE_CSV = "src/MotorPH_Credentials.csv";
    private static final String EMPLOYEE_DETAILS_CSV = "src/MotorPH Employee Data - Employee Details.csv";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginGUI loginScreen = new LoginGUI();
            loginScreen.setVisible(true);
        });
    }

public static void handleLogin(String username, String password) {
    try {
        int empID = LoginSystem.validateEmployeeLogin(username, password);

        if (empID != -1) {
            JOptionPane.showMessageDialog(null, "Login Successful! Employee ID: " + empID);
            EmployeeDashboardGUI dashboard = new EmployeeDashboardGUI();
            dashboard.setEmployeeID(String.valueOf(empID));
            dashboard.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Invalid username or password.", "Login Failed", JOptionPane.ERROR_MESSAGE);
        }

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        e.printStackTrace();
    }
}

}
