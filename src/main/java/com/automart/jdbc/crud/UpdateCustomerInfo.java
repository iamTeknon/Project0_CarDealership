package com.automart.jdbc.crud;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementCustomerDao;
import com.automart.jdbc.entities.Customer;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UpdateCustomerInfo {

    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private static final SignInPad sip = new SignInPad();
    private static final Customer customer = new Customer();
    private String newEmail;
    private String newNumber;
    private String newAddress;
    private String newCity;
    private String newState;
    private String newZip;
    private int customerId;

    public UpdateCustomerInfo(){
    }

    public void updateInfo(int customerId) throws SQLException, NonExistentEntityException {
        try{
            Customer customer = getCustomer(customerId);
        }catch(NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("I'm sorry, but that customer is not in the database. Please make sure " +
                    "you have entered the correct customer id number. You will now be redirected " +
                    "to the sign in options menu.");
            sip.signInOptions();
        }

        System.out.println("Please enter " +
                "'u' to update customers information in the database, " +
                "'r' to remove the customer from the database," +
                " or 'x' to exit: ");
        String updateOption = scan.nextLine();
        switch (updateOption){
            case "u":
                System.out.println("Please enter the customers first name: ");
                String newFirstName = scan.nextLine();
                customer.setFirstName(newFirstName);
                System.out.println("Please enter the customers last name: ");
                String newLastName = scan.nextLine();
                customer.setLastName(newLastName);
                System.out.println("Please enter the customers phone number: ");
                newNumber = scan.nextLine();
                customer.setPhone(newNumber);
                System.out.println("Please enter the customers email: ");
                String newEmail = scan.nextLine();
                customer.setEmail(newEmail);
                System.out.println("Please enter the customers street address: ");
                String newAddress = scan.nextLine();
                customer.setAddress(newAddress);
                System.out.println("Please enter the customers city: ");
                String newCity = scan.nextLine();
                customer.setCity(newCity);
                System.out.println("Please enter the customers state: ");
                String newState = scan.nextLine();
                customer.setState(newState);
                System.out.println("Please enter the customers zip code: ");
                String newZip = scan.nextLine();
                customer.setZip(newZip);
                customer.setId(customerId);
                updateCustomer(customer);
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
            case "r":
                customer.setId(customerId);
                deleteCustomer(customer);
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
    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    public static void updateCustomer(Customer customer) {
        CUSTOMER_DAO.update(customer);
    }

    public static void deleteCustomer(Customer customer) {
        CUSTOMER_DAO.delete(customer);
    }
}
