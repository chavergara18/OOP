package com.mycompany.oopbasedpayrollsystem;

import java.time.LocalDate;
import java.util.*;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class TestPayslip {

    public static void main(String[] args) {
        // Create sample data
        PayslipData data = new PayslipData(
            "EMP001",                       // Employee ID
            "Juan",                         // First Name
            "Dela Cruz",                    // Last Name
            "Software Engineer",            // Position
            25000.00,                       // Basic Salary
            3000.00,                        // Allowance
            1000.00,                        // SSS Deduction
            800.00,                         // PhilHealth Deduction
            600.00,                         // Pag-IBIG Deduction
            2500.00,                        // BIR Tax
            23700.00,                       // Net Salary
            LocalDate.now().toString()      // Generated Date
        );

        try {
            // Load and compile the JRXML file
            JasperReport report = JasperCompileManager.compileReport("reports/payslip_template_fixed.jrxml");

            // Wrap the bean in a list
            List<PayslipData> dataList = Collections.singletonList(data);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            // Fill the report
            JasperPrint filledReport = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

            // Export to PDF
            JasperExportManager.exportReportToPdfFile(filledReport, "Payslip_EMP001.pdf");

            System.out.println("✅ Payslip generated successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error generating payslip:");
            e.printStackTrace();
        }
    }
}