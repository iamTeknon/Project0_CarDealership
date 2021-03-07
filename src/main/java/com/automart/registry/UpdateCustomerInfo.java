package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementDao;
import com.automart.jdbc.entities.Customer;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UpdateCustomerInfo {

    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementDao();
    private static final SignInPad sip = new SignInPad();
    private static final Customer customer = new Customer();
    private String newEmail;
    private String newLastName;
    private String newFirstName;
    private String newNumber;
    private String newAddress;
    private String newCity;
    private String newState;
    private String newZip;
    private int customerId;

    public UpdateCustomerInfo(){
    }

    public void updateInfo(int customerId) throws SQLException, NonExistentEntityException {

        boolean updateFlag = false;
        while(!updateFlag){
            System.out.println("Please enter " +
                    "'f' to update your first name, " +
                    "'l' to update your last name, " +
                    "'p' to update your phone number, " +
                    "'e' to update your email, " +
                    "'a' to update your street address, " +
                    "'c' to update your city, " +
                    "'s' to update your state, " +
                    "'z' to update your zip code, " +
                    "or 'x' to exit: ");
            String updateOption = scan.nextLine();
            switch (updateOption){
                case "f":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new first name: ");
                        newFirstName = scan.nextLine();
                        customer.setFirstName(newFirstName);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "l":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new last name: ");
                        newLastName = scan.nextLine();
                        customer.setLastName(newLastName);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "p":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new phone number: ");
                        newNumber = scan.nextLine();
                        customer.setPhone(newNumber);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "e":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new email: ");
                        String newEmail = scan.nextLine();
                        customer.setEmail(newEmail);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "a":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new street address: ");
                        String newAddress = scan.nextLine();
                        customer.setAddress(newAddress);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "c":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new city: ");
                        String newCity = scan.nextLine();
                        customer.setCity(newCity);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "s":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new state: ");
                        String newState = scan.nextLine();
                        customer.setState(newState);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
                    break;
                case "z":
                    try{
                        Customer customer = getCustomer(customerId);
                        System.out.println("Please enter the new zip code: ");
                        String newZip = scan.nextLine();
                        customer.setZip(newZip);
                        customer.setId(customerId);
                        updateCustomer(customer);
                    }catch (NonExistentEntityException ex) {
                        ex.getStackTrace();
                    }
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
    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    public static void updateCustomer(Customer customer) {
        CUSTOMER_DAO.update(customer);
    }
}
