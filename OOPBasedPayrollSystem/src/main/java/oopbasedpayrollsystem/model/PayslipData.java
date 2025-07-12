/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopbasedpayrollsystem.model;

import java.util.Date;

/**
 * Unified PayslipData model for use in both basic and detailed payslip reporting.
 */
public class PayslipData {
    // Basic payslip fields
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

    // Detailed payslip fields
    private int id;
    private String payslipNo;
    private int employeeId;
    private String employeeName;
    private String positionDepartment;
    private Date periodStart;
    private Date periodEnd;
    private double monthlyRate;
    private double dailyRate;
    private int daysWorked;
    private double overtimeHours;
    private double grossIncome;
    private double riceSubsidy;
    private double phoneAllowance;
    private double clothingAllowance;
    private double totalBenefits;
    private double sss;
    private double philhealth;
    private double pagibig;
    private double withholdingTax;
    private double totalDeductions;
    private double takeHomePay;

    // ✅ Default constructor for frameworks (e.g., JasperReports, DAO mapping)
    public PayslipData() {}

    // ✅ Constructor for basic data population
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

    // ======== Basic Payslip Getters ========
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

    // ======== Detailed Payslip Getters/Setters ========
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getPayslipNo() { return payslipNo; }
    public void setPayslipNo(String payslipNo) { this.payslipNo = payslipNo; }

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getEmployeeName() { return employeeName; }
    public void setEmployeeName(String employeeName) { this.employeeName = employeeName; }

    public String getPositionDepartment() { return positionDepartment; }
    public void setPositionDepartment(String positionDepartment) { this.positionDepartment = positionDepartment; }

    public Date getPeriodStart() { return periodStart; }
    public void setPeriodStart(Date periodStart) { this.periodStart = periodStart; }

    public Date getPeriodEnd() { return periodEnd; }
    public void setPeriodEnd(Date periodEnd) { this.periodEnd = periodEnd; }

    public double getMonthlyRate() { return monthlyRate; }
    public void setMonthlyRate(double monthlyRate) { this.monthlyRate = monthlyRate; }

    public double getDailyRate() { return dailyRate; }
    public void setDailyRate(double dailyRate) { this.dailyRate = dailyRate; }

    public int getDaysWorked() { return daysWorked; }
    public void setDaysWorked(int daysWorked) { this.daysWorked = daysWorked; }

    public double getOvertimeHours() { return overtimeHours; }
    public void setOvertimeHours(double overtimeHours) { this.overtimeHours = overtimeHours; }

    public double getGrossIncome() { return grossIncome; }
    public void setGrossIncome(double grossIncome) { this.grossIncome = grossIncome; }

    public double getRiceSubsidy() { return riceSubsidy; }
    public void setRiceSubsidy(double riceSubsidy) { this.riceSubsidy = riceSubsidy; }

    public double getPhoneAllowance() { return phoneAllowance; }
    public void setPhoneAllowance(double phoneAllowance) { this.phoneAllowance = phoneAllowance; }

    public double getClothingAllowance() { return clothingAllowance; }
    public void setClothingAllowance(double clothingAllowance) { this.clothingAllowance = clothingAllowance; }

    public double getTotalBenefits() { return totalBenefits; }
    public void setTotalBenefits(double totalBenefits) { this.totalBenefits = totalBenefits; }

    public double getSss() { return sss; }
    public void setSss(double sss) { this.sss = sss; }

    public double getPhilhealth() { return philhealth; }
    public void setPhilhealth(double philhealth) { this.philhealth = philhealth; }

    public double getPagibig() { return pagibig; }
    public void setPagibig(double pagibig) { this.pagibig = pagibig; }

    public double getWithholdingTax() { return withholdingTax; }
    public void setWithholdingTax(double withholdingTax) { this.withholdingTax = withholdingTax; }

    public double getTotalDeductions() { return totalDeductions; }
    public void setTotalDeductions(double totalDeductions) { this.totalDeductions = totalDeductions; }

    public double getTakeHomePay() { return takeHomePay; }
    public void setTakeHomePay(double takeHomePay) { this.takeHomePay = takeHomePay; }
}

