package com.automart.utilities;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementDao;
import com.automart.jdbc.entities.Customer;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Collection;
import java.util.Optional;
import java.util.Scanner;

public class Driver {

    Scanner scan = new Scanner(System.in);
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementDao();

    public static void main(String[] args) throws SQLException {

        SignInPad si = new SignInPad();
        si.signInOptions();

//        EmployeeRegistration er = new EmployeeRegistration();
//        er.getEmployeeInfo();

        // Test whether an exception is thrown when
        // the database is queried for a non-existent customer.
        // But, if the customer does exist, the details will be printed
        // on the console
//        try {
//            Customer customer = getCustomer(6);
//        } catch (NonExistentEntityException ex) {
//            ex.getStackTrace();
//        }
//
//        // Test whether a customer can be added to the database
//        Customer firstCustomer =
//                new Customer(1, "iam", "teknon",  "7198226564", "michael.bates@revature.net",
//                        "2611", "Tyler", "TX", "75701");
//        Customer secondCustomer =
//                new Customer(4, "Joshua", "Daulton", "8067872582", "JoshuaMDaulton@teleworm.us",
//                        "3402", "Lubbock", "TX", "79423");
//        Customer thirdCustomer =
//                new Customer(3, "April", "Ellis", "8067936544", "AprilMellis@jourrapide.com",
//                        "3100", "Colorado Springs", "CO", "93467");
//        addCustomer(firstCustomer).ifPresent(firstCustomer::setId);
//        addCustomer(secondCustomer).ifPresent(secondCustomer::setId);
//        addCustomer(thirdCustomer).ifPresent(thirdCustomer::setId);
//
//        // Test whether the new customer's details can be edited
//        secondCustomer.setFirstName("mike");
//        secondCustomer.setLastName("bates");
//        secondCustomer.setEmail("agapeteknon@gmail.com");
//        updateCustomer(secondCustomer);
//
//        // Test whether all customers can be read from database
//        getAllCustomers().forEach(System.out::println);
//
//        // Test whether a customer can be deleted
//        deleteCustomer(secondCustomer);
    }

    // Static helper methods referenced above
    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    public static Collection<Customer> getAllCustomers() {
        return CUSTOMER_DAO.getAll();
    }

    public static void updateCustomer(Customer customer) {
        CUSTOMER_DAO.update(customer);
    }

    public static Optional<Integer> addCustomer(Customer customer) {
        return CUSTOMER_DAO.save(customer);
    }

    public static void deleteCustomer(Customer customer) {
        CUSTOMER_DAO.delete(customer);
    }



}

