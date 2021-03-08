package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementEmployeeDao;
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
            System.out.println("That employee is not in the database. Please make " +
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
                    break;
                case "l":
                    System.out.println("Please enter the new last name: ");
                    String newLastName = scan.nextLine();
                    employee.setFirstName(employee.getFirstName());
                    employee.setLastName(newLastName);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    break;
                case "p":
                    System.out.println("Please enter the new phone number: ");
                    String newNumber = scan.nextLine();
                    employee.setPhone(newNumber);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    break;
                case "e":
                    System.out.println("Please enter the new email: ");
                    String newEmail = scan.nextLine();
                    employee.setEmail(newEmail);
                    employee.setId(employeeId);
                    updateEmployee(employee);
                    break;
                case "r":
                    employee.setId(employeeId);
                    deleteEmployee(employee);
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
