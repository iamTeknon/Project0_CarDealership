package com.automart.ui;

import com.automart.registry.CustomerRegistration;
import com.automart.registry.UpdateCustomerInfo;

import java.sql.SQLException;
import java.util.Scanner;

public class SignInPad {
    private static final Scanner scan = new Scanner(System.in);
    private String initialOption;
    private String customerEmail;
    private String customerPassword;
    private String viewOption;
    private String employeeEmail;
    private String employeeLastName;

    public SignInPad(){
    }

    // TODO: Need to add an update account info option
    public void signInOptions() throws SQLException {
        System.out.println("Enter " +
                "'r' to register for a customer account, " +
                "'u' to update your customer account, " +
                "'l' for customer log in, or " +
                "'e' for employee log in: ");
        initialOption = scan.nextLine();
        if(initialOption.equalsIgnoreCase("r")) {
            CustomerRegistration cr = new CustomerRegistration();
            cr.getCustomerInfo();
            signInOptions();
        }else if(initialOption.equalsIgnoreCase("u")){
            UpdateCustomerInfo uci = new UpdateCustomerInfo();
            uci.updateInfo();
            // The next 2 else-if statements need to re-direct to database
        }else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your email: ");
            customerEmail = scan.nextLine();
            System.out.println("Please enter your password: ");
            customerPassword = scan.nextLine();
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
            System.out.println("Please enter your email: ");
            employeeEmail = scan.nextLine();
            System.out.println("Please enter your last name: ");
            employeeLastName = scan.nextLine();

            // TODO: Need to match entry to db info (pre-store this info)
            // TODO: If no match then have them try 3 more times max
        }else{
            signInOptions();
        }
    }
}
