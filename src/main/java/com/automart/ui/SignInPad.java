package com.automart.ui;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.UpdateAutomartCarInfo;
import com.automart.jdbc.crud.UpdateCustomerInfo;
import com.automart.jdbc.crud.UpdateEmployeeInfo;
import com.automart.jdbc.crud.UpdateOffers;
import com.automart.jdbc.dao.*;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.jdbc.entities.Customer;
import com.automart.jdbc.entities.Employee;
import com.automart.jdbc.entities.Offers;
import com.automart.registry.AutomartCarRegistration;
import com.automart.registry.CustomerRegistration;
import com.automart.registry.EmployeeRegistration;
import com.automart.registry.OfferRegistration;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class SignInPad {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Employee, Integer> EMPLOYEE_DAO = new ImplementEmployeeDao();
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private static final Dao<Offers, Integer> OFFERS_CAR_DAO = new ImplementOffersDao();
    private int customerId;

    public SignInPad(){
    }

    public void signInOptions() throws SQLException, NonExistentEntityException {
        System.out.println("Howdy! Please enter " +
                "'r' to register for a customer account, " +
                "'l' for customer log in, or " +
                "'e' for employee log in: ");

        String initialOption = scan.nextLine();

        if(initialOption.equalsIgnoreCase("r")) {
            CustomerRegistration cr = new CustomerRegistration();
            cr.getCustomerInfo();
            signInOptions();
        }
        else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your customer ID: ");
            this.customerId = scan.nextInt();
            scan.nextLine();

            try{
                Customer c = getCustomer(customerId);
            }catch(NonExistentEntityException ex){
                ex.printStackTrace();
                System.out.println("I'm sorry, but you are not in the database. Please make sure " +
                        "you enter the correct customer id number. You will now be redirected to " +
                        "the sign in options menu.");
                signInOptions();
            }

            boolean flag = true;
            while(flag) {
                System.out.println("Thank you for shopping at Automart! Please enter " +
                    "'v' to view cars for sale, " +
                    "'o' to make an offer on a vehicle, " +
                    "'m' to view cars you previously purchased, " +
                    "'p' to view remaining payments on a car, " +
                    "or 'x' to exit: ");
                String viewOption = scan.nextLine();

                if (viewOption.equalsIgnoreCase("v")) {
                    try{
                        AutomartCar ac = getAutomartCar();
                    }catch (NonExistentEntityException ex){
                        ex.printStackTrace();
                        signInOptions();
                    }
                }
                else if(viewOption.equalsIgnoreCase("o")){
                    OfferRegistration or = new OfferRegistration("pending");
                    or.getOfferInfo();
                    signInOptions();
                }
                else if (viewOption.equalsIgnoreCase("m")) {
                    flag = false;
                    // TODO: make sql code to print out customer vehicles according to customer id
                }
                else if (viewOption.equalsIgnoreCase("p")) {
                    flag = false;
                    // TODO: figure out how to do continuous calculations on balance and remaining payments
                    //      possibly use 72 month payment plan to subtract # of months already paid to
                    //      determine how many payments left, but need to go overboard and be able to
                    //      account for overpayments, so it needs to keep track of total payments amount made
                    //      and subtract that from balance and divide stated monthly payment plan by remaining
                    //      balance
                }
                else if (viewOption.equalsIgnoreCase("x")) {
                    flag = false;
                    signInOptions();
                }
                else {
                    System.out.println("Please make sure you entered your choice correctly");
                }
            }
        }
        else if(initialOption.equalsIgnoreCase("e")){
            System.out.println("Please enter your employee ID: ");
            int employeeId = scan.nextInt();
            scan.nextLine();
            boolean employeeOptionsFlag = true;
            try{
                Employee employee = getEmployee(employeeId);
            }catch(NonExistentEntityException ex){
                ex.printStackTrace();
                System.out.println("I'm sorry, but you are not in our database. Please make sure " +
                        "you entered your employee id number correctly. You will now be redirected " +
                        "to the sign in options menu.");
                employeeOptionsFlag = false;
            }
            while(employeeOptionsFlag){
                System.out.println("Enter " +
                    "'a' to update a customer account, " +
                    "'r' to register a new employee, " +
                    "'e' to update employee info, " +
                    "'n' to add a car, " +
                    "'u' to update car , " +
                    "'o' to view offers, " +
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
                        employeeId = scan.nextInt();
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
                        try{
                            Offers o = getOffers();
                        }catch (NonExistentEntityException ex){
                            ex.printStackTrace();
                            System.out.println("Something has gone wrong. You will be redirected " +
                                    "to the sign in options menu. Sorry for any inconvenience.");
                            signInOptions();
                        }
                        UpdateOffers uo = new UpdateOffers();
                        uo.updateOffers();
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

    // The following methods were borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Employee getEmployee(int id) throws NonExistentEntityException {
        Optional<Employee> employee = EMPLOYEE_DAO.get(id);
        return employee.orElseThrow(NonExistentCustomerException::new);
    }

    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    public static AutomartCar getAutomartCar() throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.getEverything();
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }

    public static Offers getOffers() throws NonExistentEntityException {
        Optional<Offers> offer = OFFERS_CAR_DAO.getEverything();
        return offer.orElseThrow(NonExistentCustomerException::new);
    }

}
