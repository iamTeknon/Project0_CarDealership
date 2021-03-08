package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementCustomerDao;
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
        boolean updateFlag = true;
        try{
            Customer customer = getCustomer(customerId);
        }catch(NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("That customer is not in the database. Please make sure " +
                    "you have the correct customer id #.");
            updateFlag = false;
        }
        if(updateFlag){
            do{
                System.out.println("Please enter " +
                        "'f' to update customers first name, " +
                        "'l' to update customers last name, " +
                        "'p' to update customers phone number, " +
                        "'e' to update customers email, " +
                        "'a' to update customers street address, " +
                        "'c' to update customers city, " +
                        "'s' to update customers state, " +
                        "'z' to update customers zip code, " +
                        "'d' to delete customer, " +
                        "or 'x' to exit: ");
                String updateOption = scan.nextLine();
                switch (updateOption){
                    case "f":
                        System.out.println("Please enter the new first name: ");
                        String newFirstName = scan.nextLine();
                        customer.setLastName(customer.getLastName());
                        customer.setFirstName(newFirstName);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "l":
                        System.out.println("Please enter the new last name: ");
                        String newLastName = scan.nextLine();
                        customer.setFirstName(customer.getFirstName());
                        customer.setLastName(newLastName);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;

                    case "p":
                        System.out.println("Please enter the new phone number: ");
                        newNumber = scan.nextLine();
                        customer.setPhone(newNumber);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "e":
                        System.out.println("Please enter the new email: ");
                        String newEmail = scan.nextLine();
                        customer.setEmail(newEmail);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "a":
                        System.out.println("Please enter the new street address: ");
                        String newAddress = scan.nextLine();
                        customer.setAddress(newAddress);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "c":
                        System.out.println("Please enter the new city: ");
                        String newCity = scan.nextLine();
                        customer.setCity(newCity);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "s":
                        System.out.println("Please enter the new state: ");
                        String newState = scan.nextLine();
                        customer.setState(newState);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "z":
                        System.out.println("Please enter the new zip code: ");
                        String newZip = scan.nextLine();
                        customer.setZip(newZip);
                        customer.setId(customerId);
                        updateCustomer(customer);
                        break;
                    case "d":
                        customer.setId(customerId);
                        deleteCustomer(customer);
                        break;
                    case "x":
                        updateFlag = false;
                        break;
                    default:
                        break;
                }
            }while(updateFlag);
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
