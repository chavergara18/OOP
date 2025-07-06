package com.mycompany.oopbasedpayrollsystem;

import database.EmployeeDatabase;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

public class EditEmployeeDialog extends JDialog {
    public boolean isSubmitted = false;
    private JTextField nameField, positionField, departmentField, emailField, passwordField, dateHiredField,
            basicSalaryField, allowanceField, firstNameField, lastNameField, birthdayField, phoneNumberField,
            sssNumberField, philhealthNumberField, pagibigNumberField, tinNumberField, addressField,
            sssDeductionField, philhealthDeductionField, pagibigDeductionField, birTaxField;

    private JButton submitButton;
    private Employee employee;

    public EditEmployeeDialog(Frame parent, Employee employee) {
        super(parent, "Edit Employee", true);
        this.employee = employee;

        setLayout(new GridLayout(22, 2, 5, 5));

        // Add labels and text fields for each input
        add(new JLabel("Name:"));
        nameField = new JTextField(employee.getFullName());
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField(employee.getPosition());
        add(positionField);

        add(new JLabel("Department:"));
        departmentField = new JTextField(employee.getDepartment());
        add(departmentField);

        add(new JLabel("Email:"));
        emailField = new JTextField(employee.getEmail());
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JTextField(employee.getPassword());
        add(passwordField);

        add(new JLabel("Date Hired (yyyy-mm-dd):"));
        dateHiredField = new JTextField(employee.getDateHired().toString());
        add(dateHiredField);

        add(new JLabel("Basic Salary:"));
        basicSalaryField = new JTextField(String.valueOf(employee.getBasicSalary()));
        add(basicSalaryField);

        add(new JLabel("Allowance:"));
        allowanceField = new JTextField(String.valueOf(employee.getAllowance()));
        add(allowanceField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField(employee.getFirstName());
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField(employee.getLastName());
        add(lastNameField);

        add(new JLabel("Birthday (yyyy-mm-dd):"));
        birthdayField = new JTextField(employee.getBirthday().toString());
        add(birthdayField);

        add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField(employee.getPhoneNumber());
        add(phoneNumberField);

        add(new JLabel("SSS Number:"));
        sssNumberField = new JTextField(employee.getSssNumber());
        add(sssNumberField);

        add(new JLabel("PhilHealth Number:"));
        philhealthNumberField = new JTextField(employee.getPhilhealthNumber());
        add(philhealthNumberField);

        add(new JLabel("Pag-IBIG Number:"));
        pagibigNumberField = new JTextField(employee.getPagibigNumber());
        add(pagibigNumberField);

        add(new JLabel("TIN Number:"));
        tinNumberField = new JTextField(employee.getTinNumber());
        add(tinNumberField);

        add(new JLabel("Address:"));
        addressField = new JTextField(employee.getAddress());
        add(addressField);

        add(new JLabel("SSS Deduction:"));
        sssDeductionField = new JTextField(String.valueOf(employee.getSssDeduction()));
        add(sssDeductionField);

        add(new JLabel("PhilHealth Deduction:"));
        philhealthDeductionField = new JTextField(String.valueOf(employee.getPhilHealthDeduction()));
        add(philhealthDeductionField);

        add(new JLabel("Pag-IBIG Deduction:"));
        pagibigDeductionField = new JTextField(String.valueOf(employee.getPagibigDeduction()));
        add(pagibigDeductionField);

        add(new JLabel("BIR Tax:"));
        birTaxField = new JTextField(String.valueOf(employee.getBirTax()));
        add(birTaxField);

        // Add submit button
        submitButton = new JButton("Submit");
        add(submitButton);

        // Action listener for submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validate all fields
                    if (nameField.getText().isEmpty() || positionField.getText().isEmpty() || departmentField.getText().isEmpty() ||
                            emailField.getText().isEmpty() || passwordField.getText().isEmpty() || dateHiredField.getText().isEmpty() ||
                            basicSalaryField.getText().isEmpty() || allowanceField.getText().isEmpty() || firstNameField.getText().isEmpty() ||
                            lastNameField.getText().isEmpty() || birthdayField.getText().isEmpty() || phoneNumberField.getText().isEmpty() ||
                            sssNumberField.getText().isEmpty() || philhealthNumberField.getText().isEmpty() || pagibigNumberField.getText().isEmpty() ||
                            tinNumberField.getText().isEmpty() || addressField.getText().isEmpty() || sssDeductionField.getText().isEmpty() ||
                            philhealthDeductionField.getText().isEmpty() || pagibigDeductionField.getText().isEmpty() || birTaxField.getText().isEmpty()) {
                        JOptionPane.showMessageDialog(EditEmployeeDialog.this, "All fields must be filled out.");
                        return;
                    }

                    // Validate date fields
                    String dateHiredText = dateHiredField.getText();
                    String birthdayText = birthdayField.getText();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    dateFormat.setLenient(false);
                    Date dateHired = new Date(dateFormat.parse(dateHiredText).getTime());
                    Date birthday = new Date(dateFormat.parse(birthdayText).getTime());

                    // Validate numeric fields
                    double basicSalary = Double.parseDouble(basicSalaryField.getText());
                    double allowance = Double.parseDouble(allowanceField.getText());
                    double sssDeduction = Double.parseDouble(sssDeductionField.getText());
                    double philhealthDeduction = Double.parseDouble(philhealthDeductionField.getText());
                    double pagibigDeduction = Double.parseDouble(pagibigDeductionField.getText());
                    double birTax = Double.parseDouble(birTaxField.getText());

                    Map<String, Object> userdata = new HashMap<>();
                    userdata.put("id", employee.getEmployeeID());
                    userdata.put("name", nameField.getText());
                    userdata.put("position", positionField.getText());
                    userdata.put("department", departmentField.getText());
                    userdata.put("email", emailField.getText());
                    userdata.put("password", passwordField.getText());
                    userdata.put("dateHired", dateHired);
                    userdata.put("basicSalary", basicSalary);
                    userdata.put("allowance", allowance);
                    userdata.put("firstName", firstNameField.getText());
                    userdata.put("lastName", lastNameField.getText());
                    userdata.put("birthday", birthday);
                    userdata.put("phoneNumber", phoneNumberField.getText());
                    userdata.put("sssNumber", sssNumberField.getText());
                    userdata.put("philhealthNumber", philhealthNumberField.getText());
                    userdata.put("pagibigNumber", pagibigNumberField.getText());
                    userdata.put("tinNumber", tinNumberField.getText());
                    userdata.put("address", addressField.getText());
                    userdata.put("sssDeduction", sssDeduction);
                    userdata.put("philhealthDeduction", philhealthDeduction);
                    userdata.put("pagibigDeduction", pagibigDeduction);
                    userdata.put("birTax", birTax);

                    // Call UpdateEmployee method (replace with actual implementation)
                    EmployeeDatabase db = new EmployeeDatabase();
                    boolean success = db.updateEmployee(userdata);

                    if (success) {
                        JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Employee updated successfully!");
                        dispose();
                        isSubmitted = true;
                    } else {
                        JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Failed to update employee.");
                        isSubmitted = false;
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Invalid date format. Please use yyyy-MM-dd.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Invalid numeric value entered.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(EditEmployeeDialog.this, "Error: " + ex.getMessage());
                }
            }
        });

        // Set bounds and make dialog visible
        setBounds(300, 200, 600, 800);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Example usage with a dummy Employee object
            Employee dummyEmployee = new Employee(1, "John Doe", "John", "Doe", "Manager", "HR", "john.doe@example.com", "password123",
                    Date.valueOf("2020-01-01"), 50000, 10000, "09123456789", "123456789", "987654321", "123456789", "123456789",
                    "123 Main St", 2250, 1750, 1000, 6000,"PS-001", Date.valueOf("2023-12-01"), Date.valueOf("2023-12-15"));
            EditEmployeeDialog dialog = new EditEmployeeDialog(null, dummyEmployee);
            dialog.setVisible(true);
        });
    }
}