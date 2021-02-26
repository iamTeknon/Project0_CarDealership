package com.automart.ui;


import java.util.Scanner;

public class User {
    /*
        TODO: CREATE FIELDS
    */
    // For log in
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String customerEmail;

    // Additional info for registration
    private String customerAddress;

    /*
        TODO: CREATE CONSTRUCTOR(s)
    */
    public User(){
    }

    Scanner scan = new Scanner(System.in);

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName() {
        System.out.println("Howdy! Welcome to Automart! Please enter your first name: ");
        String firstName = scan.nextLine();
        this.customerFirstName = firstName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName() {
        System.out.println("Please enter your last name: ");
        String lastName = scan.nextLine();
        this.customerLastName = lastName;
    }

    public String getCustomerPhoneNumber() {
        return customerPhoneNumber;
    }

    public void setCustomerPhoneNumber() {
        System.out.println("Please enter your phone number: ");
        String phoneNumber = scan.nextLine();
        this.customerPhoneNumber = phoneNumber;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail() {
        System.out.println("Please enter your email address: ");
        String email = scan.nextLine();
        this.customerEmail = email;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /*
        TODO: CREATE METHODS
            employeeLogIn
            customerLogIn
            customerAccountRegistration
     */
}
