/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package oopbasedpayrollsystem.model;

/**
 *
 * @author Cha
 */
import oopbasedpayrollsystem.gui.EmployeeDashboardGUI;
        
public class EmployeeUser extends User {
    
    public EmployeeUser(int employeeID, String firstName, String lastName, java.time.LocalDate birthday,
                        String address, String phoneNumber, String sssNumber, String philHealthNumber,
                        String tinNumber, String pagIbigNumber, String status, String position,
                        String username, String password) {
        super(employeeID, firstName, lastName, birthday, address, phoneNumber, sssNumber,
              philHealthNumber, tinNumber, pagIbigNumber, status, position, username, password);
    }

    @Override
    public void openDashboard() {
        EmployeeDashboardGUI gui = new EmployeeDashboardGUI();
        gui.setEmployeeID(String.valueOf(getEmployeeID()));
        gui.setVisible(true);
    }
    
}
