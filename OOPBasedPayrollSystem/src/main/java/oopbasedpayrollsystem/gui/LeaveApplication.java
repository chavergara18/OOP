package oopbasedpayrollsystem.gui;

import oopbasedpayrollsystem.util.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class LeaveApplication {

    public static boolean fileLeave(int employeeID, String leaveType, LocalDate startDate, LocalDate endDate, String status) {
        String sql = "INSERT INTO leave_applications (employee_id, leave_type, start_date, end_date, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, employeeID);
            stmt.setString(2, leaveType);
            stmt.setDate(3, java.sql.Date.valueOf(startDate));
            stmt.setDate(4, java.sql.Date.valueOf(endDate));
            stmt.setString(5, status);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0;

        } catch (SQLException e) {
            System.err.println("❌ Error saving leave application: " + e.getMessage());
            return false;
        }
    }
}