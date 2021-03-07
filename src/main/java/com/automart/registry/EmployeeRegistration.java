package com.automart.registry;

import com.automart.jdbc.fill.EmployeeRowFiller;

import java.sql.SQLException;
import java.util.Scanner;

public class EmployeeRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public EmployeeRegistration(){
    }

    EmployeeRowFiller erf = new EmployeeRowFiller();

    public void getEmployeeInfo() throws SQLException {
        System.out.println("Enter employee first name: ");
        firstName = scan.nextLine();

        System.out.println("Enter employee last name: ");
        lastName = scan.nextLine();

        System.out.println("Enter employee email: ");
        email = scan.nextLine();

        System.out.println("Enter employee phone number: ");
        phone = scan.nextLine();

        erf.insertRecord(lastName, firstName, email, phone);
    }
}
