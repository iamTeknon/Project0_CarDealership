package com.automart.ui;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementEmployeeDao;
import com.automart.jdbc.entities.Employee;
import com.automart.registry.CustomerRegistration;
import com.automart.registry.UpdateCustomerInfo;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class SignInPad {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Employee, Integer> EMPLOYEE_DAO = new ImplementEmployeeDao();
    private String initialOption;
    private int customerId;
    private String viewOption;
    private int employeeId;

    public SignInPad(){
    }

    // TODO: Need to add an update account info option
    public void signInOptions() throws SQLException, NonExistentEntityException {
        System.out.println("Enter " +
                "'r' to register for a customer account, " +
                "'l' for customer log in, or " +
                "'e' for employee log in: ");
        initialOption = scan.nextLine();
        if(initialOption.equalsIgnoreCase("r")) {
            CustomerRegistration cr = new CustomerRegistration();
            cr.getCustomerInfo();
            signInOptions();
        }else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your customer ID: ");
            customerId = scan.nextInt();

            // TODO: Need to match entries to db info
            // TODO: If no match then have them try 3 more times max

            // As long as everything is good with log in
            boolean flag = true;
            while(flag) {
                System.out.println("Enter 'v' to view cars for sale, 'm' to view cars you previously purchased," +
                        " 'p' to view remaining payments on a car, or 'e' to exit: ");
                viewOption = scan.nextLine();

                if (viewOption.equalsIgnoreCase("v")) {
                    flag = false;
                    // TODO: Jump to car registry db

                } else if (viewOption.equalsIgnoreCase("m")) {
                    flag = false;
                    // TODO: Jump to customer account registry db

                } else if (viewOption.equalsIgnoreCase("p")) {
                    flag = false;
                    // TODO: Jump to balance column in CustomerCars table

                } else if (viewOption.equalsIgnoreCase("e")) {
                    flag = false;
                    signInOptions();
                } else {
                    System.out.println("Please make sure you entered your choice correctly");
                }
            }
        }else if(initialOption.equalsIgnoreCase("e")){
            System.out.println("Please enter your employee ID: ");
            employeeId = scan.nextInt();
            boolean employeeOptionsFlag = true;
            try{
                Employee employee = getEmployee(employeeId);
            }catch(NonExistentEntityException ex){
                ex.printStackTrace();
                System.out.println("That employee is not in the database. Please make sure " +
                        "you entered the correct employee id number.");
                employeeOptionsFlag = false;
            }
            while(employeeOptionsFlag){
                System.out.println("Enter " +
                    "'u' to update a customer account, " +
                    "'a' to add a car, " +
                    "'r' to remove a car, " +
                    "'o' to view an offer, " +
                    "or 'e' to exit: ");
                String employeeOption = scan.nextLine();
                switch (employeeOption){
                    case "u":
                        System.out.println("Please enter the customer id number: ");
                        int id = scan.nextInt();
                        UpdateCustomerInfo uci = new UpdateCustomerInfo();
                        uci.updateInfo(id);
                        break;
                    case "a":
                        // TODO: add code to add car
                        break;
                    case "r":
                        // TODO: add code to remove car
                        break;
                    case "o":
                        // TODO: add code to review offers and option to either accept or reject
                        break;
                    case "e":
                        employeeOptionsFlag = false;
                        signInOptions();
                        break;
                    default:
                        break;
                }

            }

        }
        signInOptions();
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Employee getEmployee(int id) throws NonExistentEntityException {
        Optional<Employee> employee = EMPLOYEE_DAO.get(id);
        return employee.orElseThrow(NonExistentCustomerException::new);
    }

}
