package com.automart.ui;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.finance.Offers;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
import com.automart.jdbc.crud.ImplementCustomerDao;
import com.automart.jdbc.crud.ImplementEmployeeDao;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.jdbc.entities.Customer;
import com.automart.jdbc.entities.Employee;
import com.automart.registry.*;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class SignInPad {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Employee, Integer> EMPLOYEE_DAO = new ImplementEmployeeDao();
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
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
            scan.nextLine();
            try{
                Customer customer = getCustomer(customerId);
            }catch(NonExistentEntityException ex){
                ex.printStackTrace();
                System.out.println("That customer is not in the database. Please make sure " +
                        "you have the correct customer id #.");
                signInOptions();
            }

            boolean flag = true;
            while(flag) {
                System.out.println("Enter " +
                    "'v' to view cars for sale, " +
                    "'o' to make an offer on a vehicle, " +
                    "'m' to view cars you previously purchased, " +
                    "'p' to view remaining payments on a car, " +
                    "or 'e' to exit: ");
                viewOption = scan.nextLine();

                if (viewOption.equalsIgnoreCase("v")) {
                    try{
                        AutomartCar ac = getAutomartCar();
                    }catch (NonExistentEntityException ex){
                        ex.printStackTrace();
                        System.out.println("That car is not in the database. Please make " +
                                "sure you entered the correct Automart car id number.");
                    }

                }else if(viewOption.equalsIgnoreCase("o")){
                    System.out.println("Please enter the Automart car id number for the vehicle you " +
                            "would like to make an offer on: ");
                    int myCarChoice = scan.nextInt();
                    scan.nextLine();
                    Offers offer = new Offers();
                    offer.makeOffer(myCarChoice);
                    signInOptions();
                }else if (viewOption.equalsIgnoreCase("m")) {
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
            scan.nextLine();
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
                    "'a' to update a customer account, " +
                    "'r' to register a new employee, " +
                    "'e' to update employee info, " +
                    "'n' to add a car, " +
                    "'u' to update car , " +
                    "'o' to view an offer, " +
                    "or 'x' to exit: ");
                String employeeOption = scan.nextLine();
                switch (employeeOption){
                    case "a":
                        System.out.println("Please enter the customer id number: ");
                        int customerId = scan.nextInt();
                        scan.nextLine();
                        UpdateCustomerInfo uci = new UpdateCustomerInfo();
                        uci.updateInfo(customerId);
                        break;
                    case "r":
                        EmployeeRegistration er = new EmployeeRegistration();
                        er.getEmployeeInfo();
                        break;
                    case "e":
                        System.out.println("Please enter the employee id number: ");
                        int employeeId = scan.nextInt();
                        scan.nextLine();
                        UpdateEmployeeInfo uei = new UpdateEmployeeInfo();
                        uei.updateInfo(employeeId);
                        break;
                    case "n":
                        AutomartCarRegistration acr = new AutomartCarRegistration();
                        acr.getAutomartCarInfo();
                        break;
                    case "u":
                        System.out.println("Please enter the Automart car id number: ");
                        int automartCarId = scan.nextInt();
                        scan.nextLine();
                        UpdateAutomartCarInfo uaci = new UpdateAutomartCarInfo();
                        uaci.updateCarInfo(automartCarId);
                        break;
                    case "o":
                        // TODO: add code to review offers and option to either accept or reject
                        break;
                    case "x":
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

    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static AutomartCar getAutomartCar() throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.getEverything();
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }

}
