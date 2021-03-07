package com.automart.registry;

import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementDao;
import com.automart.jdbc.entities.Customer;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Scanner;

public class UpdateCustomerInfo {

    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementDao();
    private static final SignInPad sip = new SignInPad();
    private String email;
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zip;
    private int id;

    public UpdateCustomerInfo(){
    }

    public void updateInfo() throws SQLException {
        System.out.println("Please enter your registered first name: ");
        firstName = scan.nextLine();
        System.out.println("Please enter your registered last name: ");
        lastName = scan.nextLine();
        System.out.println("Please enter your registered phone number: ");
        phoneNumber = scan.nextLine();
        System.out.println("Please enter your registered email: ");
        email = scan.nextLine();
        System.out.println("Please enter your registered street address: ");
        address = scan.nextLine();
        System.out.println("Please enter your registered city: ");
        city = scan.nextLine();
        System.out.println("Please enter your registered state: ");
        state = scan.nextLine();
        System.out.println("Please enter your registered zip code: ");
        zip = scan.nextLine();
        System.out.println("Please enter your registered Social Secuity Number: ");
        id = scan.nextInt();
        scan.nextLine();

        Customer customer = new Customer(id, firstName, lastName, phoneNumber, email, address, city, state, zip);

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
                    System.out.println("Please enter the new first name: ");
                    String newFirstName = scan.nextLine();
                    customer.setFirstName(newFirstName);
                    break;
                case "l":
                    System.out.println("Please enter the new last name: ");
                    String newLastName = scan.nextLine();
                    customer.setLastName(newLastName);
                    break;
                case "p":
                    System.out.println("Please enter the new phone number: ");
                    String newNumber = scan.nextLine();
                    customer.setPhone(newNumber);
                    break;
                case "e":
                    System.out.println("Please enter the new email: ");
                    String newEmail = scan.nextLine();
                    customer.setEmail(newEmail);
                    break;
                case "a":
                    System.out.println("Please enter the new street address: ");
                    String newAddress = scan.nextLine();
                    customer.setAddress(newAddress);
                    break;
                case "c":
                    System.out.println("Please enter the new city: ");
                    String newCity = scan.nextLine();
                    customer.setCity(newCity);
                    break;
                case "s":
                    System.out.println("Please enter the new state: ");
                    String newState = scan.nextLine();
                    customer.setState(newState);
                    break;
                case "z":
                    System.out.println("Please enter the new zip code: ");
                    String newZip = scan.nextLine();
                    customer.setZip(newZip);
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


    public static void updateCustomer(Customer customer) {
        CUSTOMER_DAO.update(customer);
    }
}
