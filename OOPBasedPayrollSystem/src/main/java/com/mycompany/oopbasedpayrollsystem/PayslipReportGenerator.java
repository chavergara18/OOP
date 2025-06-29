package com.mycompany.oopbasedpayrollsystem;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.*;

public class PayslipReportGenerator {

    public void generateReport(PayslipData data, String outputPath) {
        try {
            // Compile the JRXML template from the reports folder
            JasperReport report = JasperCompileManager.compileReport("reports/payslip_template.jrxml");

            // Wrap data into a data source
            List<PayslipData> dataList = Collections.singletonList(data);
            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(dataList);

            // Fill the report with data
            JasperPrint filledReport = JasperFillManager.fillReport(report, new HashMap<>(), dataSource);

            // Export to PDF
            JasperExportManager.exportReportToPdfFile(filledReport, outputPath);

            System.out.println("✅ Payslip PDF generated: " + outputPath);
        } catch (Exception e) {
            System.out.println("❌ Error generating payslip:");
            e.printStackTrace();
        }
    }
}