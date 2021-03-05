package com.automart.registry;

import com.automart.db.CustomerCarRowFiller;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerCarRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private String email;
    private int year;
    private String make;
    private String model;
    private String color;
    private BigDecimal balance;
    private BigDecimal monthly_payment;

    CustomerCarRowFiller ccrf = new CustomerCarRowFiller();

    public CustomerCarRegistration(){
    }

    public void purchasedCarInfo() throws SQLException {
        boolean emailFlag = false;
        email = "";
        while(!emailFlag){
            System.out.println("Enter customer email used when customer registered: ");
            String emailCheck = scan.nextLine();
            String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+" +
                    "[a-zA-Z]{2,7}$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(emailCheck);
            if(m.matches()){
                email = emailCheck;
                emailFlag = true;
            }
            else{
                System.out.println("Invalid email entry");
            }
        }
        System.out.println("Enter the year of the purchased car: ");
        year = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the make of the purchased car: ");
        make = scan.nextLine();

        System.out.println("Enter the model of the purchased car: ");
        model = scan.nextLine();

        System.out.println("Enter the color of the purchased car: ");
        color = scan.nextLine();;

        System.out.println("Enter the total balance due for the purchased car: ");
        balance = scan.nextBigDecimal();

        System.out.println("Enter the calculated monthly payments for the purchased car: ");
        monthly_payment = scan.nextBigDecimal();

        ccrf.insertRecord(email, year, make, model, color, balance, monthly_payment);

    }
}
