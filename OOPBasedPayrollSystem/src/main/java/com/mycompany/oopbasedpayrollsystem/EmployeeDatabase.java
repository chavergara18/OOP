/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class EmployeeDatabase {
    private static final String BASE_PATH = System.getProperty("user.dir") + File.separator + "src" + File.separator;
    private static final String EMPLOYEE_DETAILS_CSV = BASE_PATH + "MotorPH Employee Data - Employee Details.csv";
    private static final String CREDENTIALS_CSV = BASE_PATH + "MotorPH_Credentials.csv";

    private final Map<Integer, Employee> employees = new HashMap<>();
    private final Map<String, Credential> credentials = new HashMap<>();

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final int EXPECTED_COLUMN_COUNT = 20; // Ensure CSV has this many columns

    public EmployeeDatabase() {
        loadEmployeeData();
        loadCredentials();
    }

    private void loadEmployeeData() {
    File file = new File(EMPLOYEE_DETAILS_CSV);
    if (!file.exists()) {
        System.out.println("❌ Employee CSV file not found: " + EMPLOYEE_DETAILS_CSV);
        return;
    }

    try (CSVReader csvReader = new CSVReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
        String[] data;
        csvReader.readNext(); // Skip header
        int rowNumber = 0;

        while ((data = csvReader.readNext()) != null) {
            rowNumber++;

            // ✅ Auto-Fill Missing Columns Instead of Skipping
            if (data.length < EXPECTED_COLUMN_COUNT) {
                System.out.println("⚠️ Row " + rowNumber + " has " + data.length + " columns instead of " + EXPECTED_COLUMN_COUNT + ". Filling missing values.");
                data = Arrays.copyOf(data, EXPECTED_COLUMN_COUNT); // Fill missing columns with default values
                for (int i = data.length; i < EXPECTED_COLUMN_COUNT; i++) {
                    data[i] = "0"; // Assign default value
                }
            }

            try {
                int empID = parseIntSafely(data[0], rowNumber);
                String lastName = safeTrim(data[1]);
                String firstName = safeTrim(data[2]);
                LocalDate birthDate = parseDateSafely(data[3], rowNumber);
                String address = safeTrim(data[4]);
                String phone = safeTrim(data[5]);
                String sss = cleanNumber(data[6]);
                String tin = cleanNumber(data[7]);
                String philHealth = cleanNumber(data[8]);
                String pagibig = cleanNumber(data[9]);
                String status = safeTrim(data[10]);
                String position = safeTrim(data[11]);

                // ✅ PRINT DEBUG INFO TO VERIFY CSV VALUES
                System.out.println("📝 DEBUG Row " + rowNumber + " Data:");
                for (int i = 0; i < data.length; i++) {
                    System.out.println("    Column " + i + ": " + data[i]);
                }

                // ✅ Ensure Basic Salary is Properly Loaded
                double basicSalary = parseDoubleSafely(data[13], rowNumber);
                if (basicSalary == 0.0) {
                    System.out.println("⚠️ Warning: Basic Salary is 0.0 for Employee ID " + empID + ". Check CSV data!");
                }

                // ✅ Ensure Allowances are Properly Loaded
                double riceSubsidy = parseDoubleSafely(data[14], rowNumber);
                double phoneAllowance = parseDoubleSafely(data[15], rowNumber);
                double clothingAllowance = parseDoubleSafely(data[16], rowNumber);
                double totalAllowance = riceSubsidy + phoneAllowance + clothingAllowance;

                // ✅ Fix Gross Salary Calculation
                double grossSalary = basicSalary + totalAllowance;

                // ✅ Ensure Deductions Are Loaded Correctly
                double sssDeduction = PayrollCalculator.computeSSS(grossSalary);
                double philHealthDeduction = PayrollCalculator.computePhilHealth(grossSalary);
                double pagibigDeduction = PayrollCalculator.computePagIbig(grossSalary);
                double birTax = PayrollCalculator.computeTax(grossSalary);
                
                

                // ✅ PRINT DEBUG INFO FOR DEDUCTIONS
                System.out.println("📝 DEBUG Deduction Values for Employee ID: " + empID);
                System.out.println("   Basic Salary: " + basicSalary);
                System.out.println("   PhilHealth Deduction: " + philHealthDeduction);
                System.out.println("   SSS Deduction: " + sssDeduction);
                System.out.println("   Pag-ibig Deduction: " + pagibigDeduction);
                System.out.println("   BIR Tax: " + birTax);

                // ✅ Correct Total Deductions Calculation
                double totalDeductions = sssDeduction + philHealthDeduction + pagibigDeduction + birTax;

                // ✅ Correct Net Salary Calculation
                double netSalary = grossSalary - totalDeductions;

                Employee emp = new Employee(empID, firstName, lastName, birthDate, address, phone,
                        sss, tin, philHealth, pagibig, status, position, basicSalary, totalAllowance,
                        sssDeduction, philHealthDeduction, pagibigDeduction, birTax,
                        "defaultUsername", "defaultPassword");

                employees.put(empID, emp);
                
                // ✅ Debugging Logs to Verify Data
                System.out.println("✅ Loaded Employee: " + emp.getFullName() + " (ID: " + empID + ")");
                System.out.println("   💰 Basic Salary: " + emp.getBasicSalary());
                System.out.println("   📌 Allowance: " + emp.getAllowance());
                System.out.println("   💸 Total Deductions: " + totalDeductions);
                System.out.println("   💰 Gross Salary: " + grossSalary);
                System.out.println("   💰 Net Salary: " + netSalary);
            
            } catch (Exception e) {
                System.out.println("❌ Error processing row " + rowNumber + ": " + e.getMessage());
            }
        }

        System.out.println("✅ Employee data loaded successfully. Total Employees: " + employees.size());
        System.out.println("📝 Available Employee IDs: " + employees.keySet());
        
    } catch (IOException | CsvValidationException e) {
        System.out.println("❌ Error loading employee data: " + e.getMessage());
    }
}


    private void loadCredentials() {
        File file = new File(CREDENTIALS_CSV);
        if (!file.exists()) {
            System.out.println("❌ Credentials CSV file not found: " + CREDENTIALS_CSV);
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            br.readLine(); // Skip header
            int rowNumber = 0;

            while ((line = br.readLine()) != null) {
                rowNumber++;
                String[] data = line.split(",");
                if (data.length < 3) {
                    System.out.println("⚠️ Skipping invalid row " + rowNumber + ": " + line);
                    continue;
                }
                int empID = parseIntSafely(data[0], rowNumber);
                String username = safeTrim(data[1]);
                String password = safeTrim(data[2]);

                credentials.put(username, new Credential(empID, username, password));
            }
            System.out.println("✅ Credentials loaded successfully. Total: " + credentials.size());
        } catch (IOException e) {
            System.out.println("❌ Error loading credentials: " + e.getMessage());
        }
    }

    public Employee getEmployeeByID(int empID) {
        Employee emp = employees.get(empID);
        if (emp == null) {
            System.out.println("⚠️ Employee with ID " + empID + " NOT found in database!");
            System.out.println("📝 Available Employee IDs: " + employees.keySet());
        } else {
            System.out.println("✅ Employee Found: " + emp.getFullName());
        }
        return emp;
    }

    public int validateLogin(String username, String password) {
        Credential cred = credentials.get(username);
        if (cred != null) {
            if (cred.getPassword().equals(password)) {
                int empID = cred.getEmployeeID();
                System.out.println("✅ Login Successful - Employee ID: " + empID);
                return empID;
            } else {
                System.out.println("❌ Login Failed - Incorrect Password");
            }
        } else {
            System.out.println("❌ Login Failed - Username Not Found");
        }
        return -1;
    }

    // ✅ Utility Functions for Parsing
    private String cleanNumber(String value) {
        return safeTrim(value).replaceAll("[^0-9]", "");
    }

    private int parseIntSafely(String value, int rowNumber) {
        try {
            return Integer.parseInt(safeTrim(value));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid integer format in row " + rowNumber + ": " + value);
            return 0;
        }
    }

    private double parseDoubleSafely(String value, int rowNumber) {
        try {
            return safeTrim(value).isEmpty() ? 0.0 : Double.parseDouble(safeTrim(value));
        } catch (NumberFormatException e) {
            System.out.println("⚠️ Invalid number format in row " + rowNumber + ": " + value);
            return 0.0;
        }
    }

    private LocalDate parseDateSafely(String value, int rowNumber) {
        try {
            return LocalDate.parse(safeTrim(value), DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            System.out.println("⚠️ Invalid date format in row " + rowNumber + ": " + value);
            return LocalDate.now();
        }
    }

    private String safeTrim(String value) {
        return (value == null) ? "" : value.trim();
    }
}








































































































