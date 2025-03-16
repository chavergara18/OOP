/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.oopbasedpayrollsystem;

/**
 *
 * @author User
 */
import javax.swing.JOptionPane;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Payslip extends javax.swing.JFrame {
    private String employeeID;

    /**
     * Creates new form Payslip
     */
    private EmployeeDashboardGUI dashboard; 

public Payslip(EmployeeDashboardGUI dashboard, String employeeID) {
    this.dashboard = dashboard; 
    this.employeeID = employeeID;
    initComponents();
    setEmployeeID(employeeID);
}
    public Payslip() {
        initComponents();   
    }
     public Payslip(String employeeID) {
        this.employeeID = employeeID;
        initComponents();
        setEmployeeID(employeeID);
    }
    public void setEmployeeID(String employeeID) {
    txtEmployeeID.setText(employeeID);
    loadEmployeeDetails(employeeID); 
}

private double parseDoubleSafe(String value, double defaultValue) {
    try {
        return Double.parseDouble(value.replaceAll("[\",]", "").trim());
    } catch (NumberFormatException e) {
        return defaultValue; 
    }
}


private String parseFormatted(String value) {
    return String.format("%.2f", parseDoubleSafe(value, 0.00));
}

private void loadEmployeeDetails(String employeeID) { 
    String csvFilePath = "C:/Users/User/OneDrive/Documents/NetBeansProjects/OOPBasedPayrollSystem/src/MotorPH Employee Data - Employee Details.csv";

    try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) { 
        String line;  
        br.readLine(); // Skip header row  

        while ((line = br.readLine()) != null) {  
            String[] data = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)"); // Handle CSV format  

            if (data.length >= 21 && data[0].trim().equals(employeeID)) { // Ensure enough columns  
                txtEmployeeID.setText(data[0].trim());  
                txtName.setText(data[2].trim() + " " + data[1].trim());  
                txtPosition.setText(data[11].trim());  
                txtEmployment.setText(data[10].trim());  
                
                // Convert salary values from CSV to double
                double basicSalary = parseDoubleSafe(data[13]);  
                double riceSubsidy = parseDoubleSafe(data[14]);  
                double phoneAllowance = parseDoubleSafe(data[15]);  
                double clothingAllowance = parseDoubleSafe(data[16]);  

                // Compute Allowance = Rice Subsidy + Phone + Clothing
                double totalAllowance = riceSubsidy + phoneAllowance + clothingAllowance;
                txtBasicSalary.setText(String.format("%.2f", basicSalary));  
                txtAllowance.setText(String.format("%.2f", totalAllowance));  
                
                // Compute Gross Salary
                double grossSalary = basicSalary + totalAllowance;
                txtGrossSalary.setText(String.format("%.2f", grossSalary));  

                // Compute deductions
                double sss = computeSSS(basicSalary);
                double philHealth = computePhilHealth(basicSalary);
                double pagIbig = computePagIbig(basicSalary);
                double tax = computeTax(basicSalary); 

                txtSSS.setText(String.format("%.2f", sss));  
                txtPhilHealth.setText(String.format("%.2f", philHealth));  
                txtPagIbig.setText(String.format("%.2f", pagIbig));  
                txtTax.setText(String.format("%.2f", tax));  

                // Compute total deductions
                double totalDeductions = sss + philHealth + pagIbig + tax;
                txtTotalDeductions.setText(String.format("%.2f", totalDeductions));

                // Compute Net Salary
                double netSalary = grossSalary - totalDeductions;
                txtNetSalary.setText(String.format("%.2f", netSalary));

                return;  
            }  
        }  

        JOptionPane.showMessageDialog(this, "Employee not found!", "Error", JOptionPane.ERROR_MESSAGE);  
    } catch (IOException e) {  
        JOptionPane.showMessageDialog(this, "Error loading employee data: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);  
    }  
}

// Safely parse double values from CSV
private double parseDoubleSafe(String value) {
    try {
        return Double.parseDouble(value.replace(",", "").replace("\"", "").trim());
    } catch (NumberFormatException e) {
        return 0.00;  // Default value if parsing fails
    }
}

// Compute SSS Contribution (Employee Share)
private double computeSSS(double basicSalary) {
    return Math.min(1620.00, basicSalary * 0.045);  // Max cap ₱1,620
}

// Compute PhilHealth Contribution (Employee Share)
private double computePhilHealth(double basicSalary) {
    double contribution = basicSalary * 0.025; // 50% of 5%  
    return Math.min(contribution, 2250.00); // Max ₱2,250.00
}

// Compute Pag-IBIG Contribution
private double computePagIbig(double basicSalary) {
    return Math.min(basicSalary * 0.01, 100.00); // Capped at ₱100.00
}

// Compute Withholding Tax (BIR TRAIN Law)
private double computeTax(double monthlySalary) {
    double annualSalary = monthlySalary * 12;
    double tax = 0.00;

    if (annualSalary <= 250000) {
        tax = 0.00;
    } else if (annualSalary <= 400000) {
        tax = (annualSalary - 250000) * 0.20;
    } else if (annualSalary <= 800000) {
        tax = 30000 + (annualSalary - 400000) * 0.25;
    } else if (annualSalary <= 2000000) {
        tax = 130000 + (annualSalary - 800000) * 0.30;
    } else {
        tax = 490000 + (annualSalary - 2000000) * 0.35;
    }

    return tax / 12;  // Convert to monthly tax
}






// Utility method to clean numeric inputs
private String cleanNumber(String input) {
    if (input == null || input.trim().isEmpty() || input.trim().equalsIgnoreCase("N/A")) {
        return "0"; // Default to 0 if invalid
    }
    return input.replace("\"", "").replace(",", "").trim();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblName = new javax.swing.JLabel();
        lblEmployeeID = new javax.swing.JLabel();
        lblPosition = new javax.swing.JLabel();
        lblEmployment = new javax.swing.JLabel();
        lblSalaryBreakdown = new javax.swing.JLabel();
        lblDeductions = new javax.swing.JLabel();
        lblBasicSalary = new javax.swing.JLabel();
        lblAllowance = new javax.swing.JLabel();
        lblGrossSalary = new javax.swing.JLabel();
        lblPhilHealth = new javax.swing.JLabel();
        lblSSS = new javax.swing.JLabel();
        lblTax = new javax.swing.JLabel();
        lblTotalDeductions = new javax.swing.JLabel();
        lblNetSalary = new javax.swing.JLabel();
        lblPagIbig = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        txtEmployeeID = new javax.swing.JTextField();
        txtEmployment = new javax.swing.JTextField();
        txtBasicSalary = new javax.swing.JTextField();
        txtAllowance = new javax.swing.JTextField();
        txtGrossSalary = new javax.swing.JTextField();
        txtPosition = new javax.swing.JTextField();
        txtSSS = new javax.swing.JTextField();
        txtPagIbig = new javax.swing.JTextField();
        txtPhilHealth = new javax.swing.JTextField();
        txtTax = new javax.swing.JTextField();
        txtTotalDeductions = new javax.swing.JTextField();
        txtNetSalary = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblName.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblName.setText("Name");

        lblEmployeeID.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmployeeID.setText("Employee ID");

        lblPosition.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPosition.setText("Position");

        lblEmployment.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblEmployment.setText("Employment");

        lblSalaryBreakdown.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSalaryBreakdown.setText("Salarty Breakdown");

        lblDeductions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblDeductions.setText("Deductions");

        lblBasicSalary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblBasicSalary.setText("Basic Salary");

        lblAllowance.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblAllowance.setText("Allowance");

        lblGrossSalary.setFont(new java.awt.Font("Segoe UI", 3, 12)); // NOI18N
        lblGrossSalary.setText("Gross Salary");

        lblPhilHealth.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPhilHealth.setText("PhilHealth:");

        lblSSS.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblSSS.setText("SSS:");

        lblTax.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTax.setText("Tax (BIR):");

        lblTotalDeductions.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblTotalDeductions.setText("Total Deductions:");

        lblNetSalary.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblNetSalary.setText("Net Salary:");

        lblPagIbig.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblPagIbig.setText("Pag-ibig:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        txtEmployeeID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmployeeIDActionPerformed(evt);
            }
        });

        txtEmployment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmploymentActionPerformed(evt);
            }
        });

        txtBasicSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtBasicSalaryActionPerformed(evt);
            }
        });

        txtAllowance.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAllowanceActionPerformed(evt);
            }
        });

        txtGrossSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtGrossSalaryActionPerformed(evt);
            }
        });

        txtPosition.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPositionActionPerformed(evt);
            }
        });

        txtSSS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSSSActionPerformed(evt);
            }
        });

        txtPagIbig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPagIbigActionPerformed(evt);
            }
        });

        txtPhilHealth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPhilHealthActionPerformed(evt);
            }
        });

        txtTax.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTaxActionPerformed(evt);
            }
        });

        txtTotalDeductions.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalDeductionsActionPerformed(evt);
            }
        });

        txtNetSalary.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNetSalaryActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 102));

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButton3.setText("BACK");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        btnExit.setText("EXIT");
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(btnExit))
                .addContainerGap(59, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(0, 0, 102));

        lblTitle.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lblTitle.setForeground(new java.awt.Color(255, 255, 255));
        lblTitle.setText("MotorPH Payroll System");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(lblTitle)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(lblTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(142, 142, 142)
                .addComponent(lblSalaryBreakdown, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(186, 186, 186))
            .addGroup(layout.createSequentialGroup()
                .addGap(60, 60, 60)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblGrossSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtGrossSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 145, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblSSS, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtSSS, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPhilHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPhilHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTax, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPagIbig, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPagIbig, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(135, 135, 135))
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPosition, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployeeID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPosition, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblName, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmployment, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSalaryBreakdown, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSSS, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtBasicSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSSS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPhilHealth, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtAllowance, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPhilHealth, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblGrossSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblPagIbig, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGrossSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPagIbig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTax, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTax, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTotalDeductions, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNetSalary, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        txtName.setText(txtName.getText().trim());
    }//GEN-LAST:event_txtNameActionPerformed

    private void txtEmployeeIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmployeeIDActionPerformed
        loadEmployeeDetails(txtEmployeeID.getText().trim());
        
    }//GEN-LAST:event_txtEmployeeIDActionPerformed
private void txtEmployeeIDKeyTyped(java.awt.event.KeyEvent evt) {                                    
    char c = evt.getKeyChar();
    if (!Character.isDigit(c)) {
        evt.consume(); // Ignore non-numeric input
    }
}
    private void txtEmploymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmploymentActionPerformed
        txtEmployment.setText(txtEmployment.getText().trim());
    }//GEN-LAST:event_txtEmploymentActionPerformed

    private void txtBasicSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtBasicSalaryActionPerformed
         try {
        double basicSalary = Double.parseDouble(txtBasicSalary.getText().trim().replace(",", ""));
        txtBasicSalary.setText(String.format("%.2f", basicSalary)); // Format to 2 decimal places
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid salary input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtBasicSalary.setText(""); // Clear incorrect input
    }
    }//GEN-LAST:event_txtBasicSalaryActionPerformed

    private void txtAllowanceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAllowanceActionPerformed
        try {
        double allowance = Double.parseDouble(txtAllowance.getText().trim().replace(",", ""));
        txtAllowance.setText(String.format("%.2f", allowance));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid allowance input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtAllowance.setText("");
    }
    }//GEN-LAST:event_txtAllowanceActionPerformed

    private void txtGrossSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtGrossSalaryActionPerformed
       try {
        double basicSalary = Double.parseDouble(txtBasicSalary.getText().trim().replace(",", ""));
        
        // ✅ Fix Allowance Calculation (Sum of All Allowances)
        double riceSubsidy = Double.parseDouble(txtAllowance.getText().trim().replace(",", ""));
        double phoneAllowance = 2000; // Based on your CSV
        double clothingAllowance = 1000; // Based on your CSV
        double totalAllowance = riceSubsidy + phoneAllowance + clothingAllowance;
        
        double grossSalary = basicSalary + totalAllowance;
        txtGrossSalary.setText(String.format("%.2f", grossSalary));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error calculating Gross Salary. Please check inputs.", "Calculation Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_txtGrossSalaryActionPerformed

    private void txtPositionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPositionActionPerformed
        txtPosition.setText(txtPosition.getText().trim());
    }//GEN-LAST:event_txtPositionActionPerformed

    private void txtSSSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSSSActionPerformed
        try {
        double sss = Double.parseDouble(txtSSS.getText().trim().replace(",", ""));
        txtSSS.setText(String.format("%.2f", sss));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid SSS input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtSSS.setText("");
    }
    }//GEN-LAST:event_txtSSSActionPerformed

    private void txtPagIbigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPagIbigActionPerformed
        try {
        double pagIbig = Double.parseDouble(txtPagIbig.getText().trim().replace(",", ""));
        txtPagIbig.setText(String.format("%.2f", pagIbig));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Pag-IBIG input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtPagIbig.setText("");
    } 
    }//GEN-LAST:event_txtPagIbigActionPerformed

    private void txtPhilHealthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPhilHealthActionPerformed
        try {
        double philHealth = Double.parseDouble(txtPhilHealth.getText().trim().replace(",", ""));
        txtPhilHealth.setText(String.format("%.2f", philHealth));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid PhilHealth input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtPhilHealth.setText("");
    }
    }//GEN-LAST:event_txtPhilHealthActionPerformed

    private void txtTaxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTaxActionPerformed
        try {
        double tax = Double.parseDouble(txtTax.getText().trim().replace(",", ""));
        txtTax.setText(String.format("%.2f", tax));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Invalid Tax input. Please enter a valid number.", "Input Error", JOptionPane.ERROR_MESSAGE);
        txtTax.setText("");
    }
    }//GEN-LAST:event_txtTaxActionPerformed

    private void txtTotalDeductionsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalDeductionsActionPerformed
        try {
        double sss = Double.parseDouble(txtSSS.getText().trim().replace(",", ""));
        double philHealth = Double.parseDouble(txtPhilHealth.getText().trim().replace(",", ""));
        
        // ✅ Fix Pag-IBIG Deduction (Should be a fixed amount, NOT 45000)
        double pagIbig = 1000;  // Fixed value for Pag-IBIG contribution
        
        double tax = Double.parseDouble(txtTax.getText().trim().replace(",", ""));
        double totalDeductions = sss + philHealth + pagIbig + tax;
        
        txtTotalDeductions.setText(String.format("%.2f", totalDeductions));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error calculating Total Deductions. Please check inputs.", "Calculation Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_txtTotalDeductionsActionPerformed

    private void txtNetSalaryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNetSalaryActionPerformed
            try {
        double grossSalary = Double.parseDouble(txtGrossSalary.getText().trim().replace(",", ""));
        double totalDeductions = Double.parseDouble(txtTotalDeductions.getText().trim().replace(",", ""));
        double netSalary = grossSalary - totalDeductions;
        txtNetSalary.setText(String.format("%.2f", netSalary));
    } catch (NumberFormatException e) {
        JOptionPane.showMessageDialog(this, "Error calculating Net Salary. Please check inputs.", "Calculation Error", JOptionPane.ERROR_MESSAGE);
    }
    }//GEN-LAST:event_txtNetSalaryActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        System.exit(0);
    }//GEN-LAST:event_btnExitActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        this.dispose();
        new LoginGUI().setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Payslip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Payslip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Payslip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Payslip.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Payslip().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExit;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lblAllowance;
    private javax.swing.JLabel lblBasicSalary;
    private javax.swing.JLabel lblDeductions;
    private javax.swing.JLabel lblEmployeeID;
    private javax.swing.JLabel lblEmployment;
    private javax.swing.JLabel lblGrossSalary;
    private javax.swing.JLabel lblName;
    private javax.swing.JLabel lblNetSalary;
    private javax.swing.JLabel lblPagIbig;
    private javax.swing.JLabel lblPhilHealth;
    private javax.swing.JLabel lblPosition;
    private javax.swing.JLabel lblSSS;
    private javax.swing.JLabel lblSalaryBreakdown;
    private javax.swing.JLabel lblTax;
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblTotalDeductions;
    private javax.swing.JTextField txtAllowance;
    private javax.swing.JTextField txtBasicSalary;
    private javax.swing.JTextField txtEmployeeID;
    private javax.swing.JTextField txtEmployment;
    private javax.swing.JTextField txtGrossSalary;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNetSalary;
    private javax.swing.JTextField txtPagIbig;
    private javax.swing.JTextField txtPhilHealth;
    private javax.swing.JTextField txtPosition;
    private javax.swing.JTextField txtSSS;
    private javax.swing.JTextField txtTax;
    private javax.swing.JTextField txtTotalDeductions;
    // End of variables declaration//GEN-END:variables
}
