package com.automart.jdbc.crud;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementEmployeeDao;
import com.automart.jdbc.entities.Employee;
import com.automart.ui.SignInPad;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UpdateEmployeeInfo {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Employee, Integer> EMPLOYEE_DAO = new ImplementEmployeeDao();
    private static final SignInPad sip = new SignInPad();
    private static final Employee employee = new Employee();

    public UpdateEmployeeInfo(){
    }

    public void updateInfo(int employeeId) throws SQLException, NonExistentEntityException {
        try{
            Employee employee = getEmployee(employeeId);
        }catch (NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("I'm sorry, but that employee is not in our database. Please make " +
                    "sure you entered the correct employee id number.");
        }
        boolean updateFlag = false;
        while(!updateFlag){
            System.out.println("Please enter " +
                    "'f' to update employees first name, " +
                    "'l' to update employees last name, " +
                    "'p' to update employees phone number, " +
                    "'e' to update employees email, " +
                    "'r' to remove an employee, " +
                    "or 'x' to exit: ");
            String updateOption = scan.nextLine();
            switch (updateOption){
                case "f":
                    System.out.println("Please enter the new first name: ");
                    String newFirstName = scan.nextLine();
                    employee.setLastName(employee.getLastName());
                    employee.setFirstName(newFirstName);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    System.out.println("The employees first name has been updated in our database.");
                    System.out.println("You will now be redirected to the sign in options menu.");
                    break;
                case "l":
                    System.out.println("Please enter the new last name: ");
                    String newLastName = scan.nextLine();
                    employee.setFirstName(employee.getFirstName());
                    employee.setLastName(newLastName);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    System.out.println("The employees last name has been updated in our database.");
                    System.out.println("You will now be redirected to the sign in options menu.");
                    break;
                case "p":
                    System.out.println("Please enter the new phone number: ");
                    String newNumber = scan.nextLine();
                    employee.setPhone(newNumber);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    System.out.println("The employees phone number has been updated in our database.");
                    System.out.println("You will now be redirected to the sign in options menu.");
                    break;
                case "e":
                    System.out.println("Please enter the new email: ");
                    String newEmail = scan.nextLine();
                    employee.setEmail(newEmail);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    System.out.println("The employees email has been updated in our database.");
                    System.out.println("You will now be redirected to the sign in options menu.");
                    break;
                case "r":
                    employee.setId(employeeId);
                    deleteEmployee(employee);
                    System.out.println("The employee has been deleted from our database.");
                    System.out.println("You will now be redirected to the sign in options menu.");
                    break;
                case "x":
                    updateFlag = true;
                    break;
                default:
                    break;
            }
        }
        sip.signInOptions();
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Employee getEmployee(int id) throws NonExistentEntityException {
        Optional<Employee> employee = EMPLOYEE_DAO.get(id);
        return employee.orElseThrow(NonExistentCustomerException::new);
    }

    public static void updateEmployee(Employee employee) {
        EMPLOYEE_DAO.update(employee);
    }

    public static void deleteEmployee(Employee employee) {
        EMPLOYEE_DAO.delete(employee);
    }
}