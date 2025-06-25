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

    public Employee(int id, String name, String firstName, String lastName, String position, String department, String email, String password,
                    Date dateHired, double basicSalary, double allowance, String phoneNumber,
                    String sssNumber, String philhealthNumber, String pagibigNumber, String tinNumber, String address,
                    double sssDeduction, double philhealthDeduction, double pagibigDeduction, double birTax) {
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

    public void setEmployeeID(int id) { this.id = id; }
    public void setFirstName(String firstName) { this.firstName = firstName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
    public void setPosition(String position) { this.position = position; }
    public void setBasicSalary(double basicSalary) { this.basicSalary = basicSalary; }
    public void setAllowance(double allowance) { this.allowance = allowance; }
    public void setSssDeduction(double sssDeduction) { this.sssDeduction = sssDeduction; }
    public void setPhilHealthDeduction(double philhealthDeduction) { this.philhealthDeduction = philhealthDeduction; }
    public void setPagibigDeduction(double pagibigDeduction) { this.pagibigDeduction = pagibigDeduction; }
    public void setBirTax(double birTax) { this.birTax = birTax; }

    public boolean isEmailValid() {
        return email != null && email.contains("@") && email.contains(".");
    }

    public boolean isPhoneNumberValid() {
        return phoneNumber != null && phoneNumber.matches("\\d{11}") && phoneNumber.startsWith("09");
    }
}