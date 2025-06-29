package com.mycompany.oopbasedpayrollsystem;

public class PayslipData {
    private String employeeID;
    private String firstName;
    private String lastName;
    private String position;
    private double basicSalary;
    private double allowance;
    private double sssDeduction;
    private double philHealthDeduction;
    private double pagibigDeduction;
    private double birTax;
    private double netSalary;
    private String generatedDate;

    public PayslipData(String employeeID, String firstName, String lastName, String position,
                       double basicSalary, double allowance, double sssDeduction,
                       double philHealthDeduction, double pagibigDeduction,
                       double birTax, double netSalary, String generatedDate) {
        this.employeeID = employeeID;
        this.firstName = firstName;
        this.lastName = lastName;
        this.position = position;
        this.basicSalary = basicSalary;
        this.allowance = allowance;
        this.sssDeduction = sssDeduction;
        this.philHealthDeduction = philHealthDeduction;
        this.pagibigDeduction = pagibigDeduction;
        this.birTax = birTax;
        this.netSalary = netSalary;
        this.generatedDate = generatedDate;
    }

    // Getters (required for JasperReports)
    public String getEmployeeID() { return employeeID; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public String getPosition() { return position; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowance() { return allowance; }
    public double getSssDeduction() { return sssDeduction; }
    public double getPhilHealthDeduction() { return philHealthDeduction; }
    public double getPagibigDeduction() { return pagibigDeduction; }
    public double getBirTax() { return birTax; }
    public double getNetSalary() { return netSalary; }
    public String getGeneratedDate() { return generatedDate; }
}