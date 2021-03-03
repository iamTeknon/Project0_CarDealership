package com.automart.ui;

import java.util.Scanner;

public class SignIn {
    private boolean flag;

    public SignIn(){
    }

    Scanner scan = new Scanner(System.in);
    User user = new User();

    // TODO: Need to add an update account info option
    public void signInOptions(){
        System.out.println("Enter 'r' to register for an account, 'l' for customer log in, " +
                "or 'e' for employee log in: ");
        String initialOption = scan.nextLine();
        if(initialOption.equalsIgnoreCase("r")){
            // TODO: need to store this info in db
            user.setCustomerFirstName();
            user.setCustomerLastName();
            user.setCustomerPhoneNumber();
            user.setCustomerEmail();
            user.setCustomerStreetAddress();
            user.setCustomerAddressCity();
            user.setCustomerAddressState();
            user.setCustomerAddressZip();
            user.setPassword();
            // TODO: This may be something done in db instead
            user.setKeyNumber();
            //bTree.addNode(this.keyNumber, this.customerFirstName);
            signInOptions();

            // The next 2 else-if statements need to re-direct to database
        }else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your email: ");
            String logInEmail = scan.nextLine();
            System.out.println("Please enter your password: ");
            String logInPassword = scan.nextLine();
            // TODO: Need to match entries to db info
            // TODO: If no match then have them try 3 more times max

            // As long as everything is good with log in
            this.flag = true;
            while(flag) {
                System.out.println("Enter 'v' to view cars for sale, 'm' to view cars you previously purchased," +
                        " 'p' to view remaining payments on a car, or 'e' to exit: ");
                String viewOption = scan.nextLine();

                if (viewOption.equalsIgnoreCase("v")) {
                    this.flag = false;
                    // jump to car registry db
                } else if (viewOption.equalsIgnoreCase("m")) {
                    this.flag = false;
                    // jump to customer account registry db
                } else if (viewOption.equalsIgnoreCase("p")) {
                    this.flag = false;
                    // jump to viewRemainng finance db
                } else if (viewOption.equalsIgnoreCase("e")) {
                    this.flag = false;
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
