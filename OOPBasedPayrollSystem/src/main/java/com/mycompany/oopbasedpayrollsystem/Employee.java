package com.mycompany.oopbasedpayrollsystem;

import java.sql.Date;

public class Employee {
    private int id;
    private String name;
    private String position;
    private String department;
    private String email;
    private String password;
    private Date dateHired;
    private double basicSalary;
    private double allowance;
    private String firstName;
    private String lastName;
    private Date birthday;
    private String phoneNumber;
    private String sssNumber;
    private String philhealthNumber;
    private String pagibigNumber;
    private String tinNumber;
    private String address;
    private double sssDeduction;
    private double philhealthDeduction;
    private double pagibigDeduction;
    private double birTax;
    private String payslipNo;
    private Date periodStart;
    private Date periodEnd;

    public Employee(int id, String name, String firstName, String lastName, String position, String department, String email, String password,
                Date dateHired, double basicSalary, double allowance, String phoneNumber,
                String sssNumber, String philhealthNumber, String pagibigNumber, String tinNumber, String address,
                double sssDeduction, double philhealthDeduction, double pagibigDeduction, double birTax,
                String payslipNo, Date periodStart, Date periodEnd) {
    this.id = id;
    this.name = name;
    this.firstName = firstName;
    this.lastName = lastName;
    this.position = position;
    this.department = department;
    this.email = email;
    this.password = password;
    this.dateHired = dateHired;
    this.basicSalary = basicSalary;
    this.allowance = allowance;
    this.phoneNumber = phoneNumber;
    this.sssNumber = sssNumber;
    this.philhealthNumber = philhealthNumber;
    this.pagibigNumber = pagibigNumber;
    this.tinNumber = tinNumber;
    this.address = address;
    this.sssDeduction = sssDeduction;
    this.philhealthDeduction = philhealthDeduction;
    this.pagibigDeduction = pagibigDeduction;
    this.birTax = birTax;
    this.payslipNo = payslipNo;
    this.periodStart = periodStart;
    this.periodEnd = periodEnd;
}
    
    // Getters
    public int getEmployeeID() { return id; }
    public String getFullName() { return name; }
    public String getPosition() { return position; }
    public String getDepartment() { return department; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Date getDateHired() { return dateHired; }
    public double getBasicSalary() { return basicSalary; }
    public double getAllowance() { return allowance; }
 
    public String getPhoneNumber() { return phoneNumber; }
    public String getSssNumber() { return sssNumber; }
    public String getPhilhealthNumber() { return philhealthNumber; }
    public String getPagibigNumber() { return pagibigNumber; }
    public String getTinNumber() { return tinNumber; }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Date getBirthday() { return birthday; }
    public String getAddress() { return address; }
    public String getEmploymentStatus() {
        return "Full-Time"; // Can be dynamic later if needed
    }

 public double getSssDeduction() {
    return basicSalary * 0.045; // Example rate: 4.5%
}

public double getPhilHealthDeduction() {
    return basicSalary * 0.035; // Example rate: 3.5%
}

public double getPagibigDeduction() {
    return basicSalary * 0.02; // Example rate: 2%
}

public double getBirTax() {
    return (basicSalary + allowance) * 0.1; // Example: 10% flat tax
}

public double getTotalDeductions() {
    return getSssDeduction() + getPhilHealthDeduction() + getPagibigDeduction() + getBirTax();
}

public double getGrossSalary() {
    return basicSalary + allowance;
}

public double calculateNetSalary() {
    return getGrossSalary() - getTotalDeductions();
}

// Placeholder values for now — replace with real logic later
public int getDaysWorked() {
    return 22; // Or fetch from attendance
}

public double getOvertimeHours() {
    return 5.0; // Replace with actual calculation
}

public double getRiceSubsidy() {
    return 1500.0;
}

public double getPhoneAllowance() {
    return 1000.0;
}

public double getClothingAllowance() {
    return 800.0;
}

public double getTotalBenefits() {
    return getRiceSubsidy() + getPhoneAllowance() + getClothingAllowance();
}

public double getMonthlyRate() {
    return basicSalary;
}

public double getDailyRate() {
    return basicSalary / 22; // Assuming 22 working days
}

public double getTakeHomePay() {
    return calculateNetSalary();
}

public double getWithholdingTax() {
    return getBirTax(); // Alias method for consistency
}

public String getPayslipNo() {
    return payslipNo;
}

public Date getPeriodStart() {
    return periodStart;
}

public Date getPeriodEnd() {
    return periodEnd;
}


}

