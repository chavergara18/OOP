package oopbasedpayrollsystem.dao;

import oopbasedpayrollsystem.model.PayslipData;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PayslipDAO {
    private Connection conn;

    public PayslipDAO(Connection conn) {
        this.conn = conn;
    }

    public PayslipData getPayslipById(int payslipId) throws SQLException {
        String query = "SELECT * FROM payslips WHERE id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, payslipId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractPayslip(rs);
                }
            }
        }
        return null;
    }

    public PayslipData getPayslipByEmployeeAndPeriod(int employeeId, String periodStart, String periodEnd) throws SQLException {
        String query = "SELECT * FROM payslips WHERE employee_id = ? AND period_start = ? AND period_end = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, employeeId);
            stmt.setString(2, periodStart);
            stmt.setString(3, periodEnd);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return extractPayslip(rs);
                }
            }
        }
        return null;
    }

    private PayslipData extractPayslip(ResultSet rs) throws SQLException {
        PayslipData data = new PayslipData();
        data.setId(rs.getInt("id"));
        data.setPayslipNo(rs.getString("payslip_no"));
        data.setEmployeeId(rs.getInt("employee_id"));
        data.setPeriodStart(rs.getDate("period_start"));
        data.setPeriodEnd(rs.getDate("period_end"));
        data.setMonthlyRate(rs.getDouble("monthly_rate"));
        data.setDailyRate(rs.getDouble("daily_rate"));
        data.setDaysWorked(rs.getInt("days_worked"));
        data.setOvertimeHours(rs.getDouble("overtime_hours"));
        data.setGrossIncome(rs.getDouble("gross_income"));
        data.setRiceSubsidy(rs.getDouble("rice_subsidy"));
        data.setPhoneAllowance(rs.getDouble("phone_allowance"));
        data.setClothingAllowance(rs.getDouble("clothing_allowance"));
        data.setTotalBenefits(rs.getDouble("total_benefits"));
        data.setSss(rs.getDouble("sss"));
        data.setPhilhealth(rs.getDouble("philhealth"));
        data.setPagibig(rs.getDouble("pagibig"));
        data.setWithholdingTax(rs.getDouble("withholding_tax"));
        data.setTotalDeductions(rs.getDouble("total_deductions"));
        data.setTakeHomePay(rs.getDouble("take_home_pay"));
        return data;
    }
}
