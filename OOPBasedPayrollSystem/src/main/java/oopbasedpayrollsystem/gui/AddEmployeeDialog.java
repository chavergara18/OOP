package oopbasedpayrollsystem.gui;

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

public class AddEmployeeDialog extends JDialog {
    boolean isSubmitted = false;
    private JTextField nameField, positionField, departmentField, emailField, passwordField, dateHiredField,
            basicSalaryField, allowanceField, firstNameField, lastNameField, birthdayField, phoneNumberField,
            sssNumberField, philhealthNumberField, pagibigNumberField, tinNumberField, addressField,
            sssDeductionField, philhealthDeductionField, pagibigDeductionField, birTaxField;

    private JButton submitButton;

    public AddEmployeeDialog(Frame parent) {
        super(parent, "Add Employee", true);
        setLayout(new GridLayout(22, 2, 5, 5));

        // Add labels and text fields for each input
        add(new JLabel("Name:"));
        nameField = new JTextField();
        add(nameField);

        add(new JLabel("Position:"));
        positionField = new JTextField();
        add(positionField);

        add(new JLabel("Department:"));
        departmentField = new JTextField();
        add(departmentField);

        add(new JLabel("Email:"));
        emailField = new JTextField();
        add(emailField);

        add(new JLabel("Password:"));
        passwordField = new JTextField();
        add(passwordField);

        add(new JLabel("Date Hired (yyyy-mm-dd):"));
        dateHiredField = new JTextField();
        add(dateHiredField);

        add(new JLabel("Basic Salary:"));
        basicSalaryField = new JTextField();
        add(basicSalaryField);

        add(new JLabel("Allowance:"));
        allowanceField = new JTextField();
        add(allowanceField);

        add(new JLabel("First Name:"));
        firstNameField = new JTextField();
        add(firstNameField);

        add(new JLabel("Last Name:"));
        lastNameField = new JTextField();
        add(lastNameField);

        add(new JLabel("Birthday (yyyy-mm-dd):"));
        birthdayField = new JTextField();
        add(birthdayField);

        add(new JLabel("Phone Number:"));
        phoneNumberField = new JTextField();
        add(phoneNumberField);

        add(new JLabel("SSS Number:"));
        sssNumberField = new JTextField();
        add(sssNumberField);

        add(new JLabel("PhilHealth Number:"));
        philhealthNumberField = new JTextField();
        add(philhealthNumberField);

        add(new JLabel("Pag-IBIG Number:"));
        pagibigNumberField = new JTextField();
        add(pagibigNumberField);

        add(new JLabel("TIN Number:"));
        tinNumberField = new JTextField();
        add(tinNumberField);

        add(new JLabel("Address:"));
        addressField = new JTextField();
        add(addressField);

        add(new JLabel("SSS Deduction:"));
        sssDeductionField = new JTextField();
        add(sssDeductionField);

        add(new JLabel("PhilHealth Deduction:"));
        philhealthDeductionField = new JTextField();
        add(philhealthDeductionField);

        add(new JLabel("Pag-IBIG Deduction:"));
        pagibigDeductionField = new JTextField();
        add(pagibigDeductionField);

        add(new JLabel("BIR Tax:"));
        birTaxField = new JTextField();
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
                        JOptionPane.showMessageDialog(AddEmployeeDialog.this, "All fields must be filled out.");
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

                    // Call AddEmployee method (replace with actual implementation)
                    EmployeeDatabase db = new EmployeeDatabase();
                    boolean success = db.AddEmployee(userdata);

                    if (success) {
                        JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Employee added successfully!");
                        dispose();
                        isSubmitted = true;
                    } else {
                        JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Failed to add employee.");
                        isSubmitted = false;
                    }
                } catch (ParseException ex) {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Invalid date format. Please use yyyy-MM-dd.");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Invalid numeric value entered.");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(AddEmployeeDialog.this, "Error: " + ex.getMessage());
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
            AddEmployeeDialog dialog = new AddEmployeeDialog(null);
            dialog.setVisible(true);
        });
    }
}