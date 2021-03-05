package com.automart.ui;

import com.automart.registry.CustomerRegistration;

import java.sql.SQLException;
import java.util.Scanner;

public class SignInPad {
    private static final Scanner scan = new Scanner(System.in);

    public SignInPad(){
    }

    User user = new User();
    CustomerRegistration cr = new CustomerRegistration();

    // TODO: Need to add an update account info option
    public void signInOptions()throws SQLException {
        System.out.println("Enter 'r' to register for an account, 'l' for customer log in, " +
                "or 'e' for employee log in: ");
        String initialOption = scan.nextLine();
        if(initialOption.equalsIgnoreCase("r")){
            cr.getRegistrationInfo();
            signInOptions();

            // The next 2 else-if statements need to re-direct to database
        }else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your email: ");
            String logInEmail = scan.nextLine();
            System.out.println("Please enter your password: ");
            String logInPassword = scan.nextLine();
            // TODO: Need to match entries to db info
            // TODO: If no match then have them try 3 more times max

            // looping flag in case customer doesn't enter available choices
            boolean flag = true;
            while(flag) {
                // TODO: Need to do a select from db to view automart cars or cars they have
                //      from automart (all columns)
                System.out.println("Enter 'v' to view cars for sale, 'm' to view cars you previously purchased," +
                        " or 'e' to exit: ");
                String viewOption = scan.nextLine();

                if (viewOption.equalsIgnoreCase("v")) {
                    flag = false;
                    // jump to car registry db
                } else if (viewOption.equalsIgnoreCase("m")) {
                    flag = false;
                    // jump to customer account registry db
                } else if (viewOption.equalsIgnoreCase("p")) {
                    flag = false;
                    // jump to viewRemainng finance db
                } else if (viewOption.equalsIgnoreCase("e")) {
                    flag = false;
                    signInOptions();
                } else {
                    System.out.println("Please make sure you entered your choice correctly");
                }
            }
        }else if(initialOption.equalsIgnoreCase("e")){
            System.out.println("Please enter your employee id: ");
            String employeeID = scan.nextLine();

            // TODO: Need to match entry to db info (pre-store this info)
            // TODO: If no match then have them try 3 more times max
        }else{
            signInOptions();
        }
    }
}
