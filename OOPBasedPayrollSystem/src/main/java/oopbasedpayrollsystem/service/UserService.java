/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package oopbasedpayrollsystem.service;

/**
 *
 * @author Cha
 */
public interface UserService {
    boolean login(String username, String password);
    String[] viewPersonalInformation();
    String[] viewSalary();
}
