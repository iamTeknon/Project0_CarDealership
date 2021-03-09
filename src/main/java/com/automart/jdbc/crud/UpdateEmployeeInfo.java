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
        System.out.println("Please enter " +
                "'u' to update employees information in the database, " +
                "'r' to remove an employee from the database, " +
                "or 'x' to exit: ");
        String updateOption = scan.nextLine();
        switch (updateOption){
            case "u":
                System.out.println("Please enter the employees first name: ");
                String newFirstName = scan.nextLine();
                employee.setFirstName(newFirstName);
                System.out.println("Please enter the employees last name: ");
                String newLastName = scan.nextLine();
                employee.setFirstName(employee.getFirstName());
                employee.setLastName(newLastName);
                System.out.println("Please enter the employess phone number: ");
                String newNumber = scan.nextLine();
                employee.setPhone(newNumber);
                System.out.println("Please enter the employees email: ");
                String newEmail = scan.nextLine();
                employee.setEmail(newEmail);
                employee.setId(employeeId);
                updateEmployee(employee);
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
            case "r":
                employee.setId(employeeId);
                deleteEmployee(employee);
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
            case "x":
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
            default:
                break;
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
