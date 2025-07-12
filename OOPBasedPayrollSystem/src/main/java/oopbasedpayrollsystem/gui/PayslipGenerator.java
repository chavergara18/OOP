
package oopbasedpayrollsystem.gui;

/**
 *
 * @author Cha
 */
import oopbasedpayrollsystem.model.Employee;
import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

import java.io.FileOutputStream;
import java.time.LocalDate;

public class PayslipGenerator {

    public static void generatePayslip(Employee employee) {
        if (employee == null) {
            System.out.println("❌ No employee data found.");
            return;
        }

        try {
            Document document = new Document(PageSize.A4);
            String filename = "Payslip_" + employee.getEmployeeID() + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filename));
            document.open();

            Font titleFont = new Font(Font.HELVETICA, 18, Font.BOLD);
            Font sectionHeaderFont = new Font(Font.HELVETICA, 14, Font.BOLD);
            Font labelFont = new Font(Font.HELVETICA, 12);
            Font boldFont = new Font(Font.HELVETICA, 12, Font.BOLD);

            Paragraph title = new Paragraph("MOTORPH PAYSLIP", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            title.setSpacingAfter(10);
            document.add(title);

            document.add(new Paragraph("Date: " + LocalDate.now(), labelFont));
            document.add(new Paragraph("Employee ID: " + employee.getEmployeeID(), labelFont));
            document.add(new Paragraph("Name: " + employee.getFirstName() + " " + employee.getLastName(), labelFont));
            document.add(new Paragraph("Position: " + employee.getPosition(), labelFont));
            document.add(Chunk.NEWLINE);

            // Earnings Table
            Paragraph earningsTitle = new Paragraph("Earnings", sectionHeaderFont);
            earningsTitle.setSpacingAfter(5);
            document.add(earningsTitle);

            PdfPTable earningsTable = new PdfPTable(2);
            earningsTable.setWidthPercentage(100);
            earningsTable.addCell(new Phrase("Basic Salary", boldFont));
            earningsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getBasicSalary()), labelFont));
            earningsTable.addCell(new Phrase("Allowance", boldFont));
            earningsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getAllowance()), labelFont));
            document.add(earningsTable);
            document.add(Chunk.NEWLINE);

            // Deductions Table
            Paragraph deductionsTitle = new Paragraph("Deductions", sectionHeaderFont);
            deductionsTitle.setSpacingAfter(5);
            document.add(deductionsTitle);

            PdfPTable deductionsTable = new PdfPTable(2);
            deductionsTable.setWidthPercentage(100);
            deductionsTable.addCell(new Phrase("SSS", boldFont));
            deductionsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getSssDeduction()), labelFont));
            deductionsTable.addCell(new Phrase("PhilHealth", boldFont));
            deductionsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getPhilHealthDeduction()), labelFont));
            deductionsTable.addCell(new Phrase("Pag-IBIG", boldFont));
            deductionsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getPagibigDeduction()), labelFont));
            deductionsTable.addCell(new Phrase("Tax", boldFont));
            deductionsTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.getBirTax()), labelFont));
            document.add(deductionsTable);
            document.add(Chunk.NEWLINE);

            // Summary Table
            Paragraph summaryTitle = new Paragraph("Summary", sectionHeaderFont);
            summaryTitle.setSpacingAfter(5);
            document.add(summaryTitle);

            PdfPTable summaryTable = new PdfPTable(2);
            summaryTable.setWidthPercentage(100);
            summaryTable.addCell(new Phrase("Net Salary", boldFont));
            summaryTable.addCell(new Phrase(String.format("PHP %,10.2f", employee.calculateNetSalary()), boldFont));
            document.add(summaryTable);

            document.close();
            System.out.println("✅ Payslip PDF generated: " + filename);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error generating payslip: " + e.getMessage());
        }
    }
}
