package com.automart.registry;

import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementEmployeeDao;
import com.automart.jdbc.entities.Employee;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmployeeRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Employee, Integer> EMPLOYEE_DAO = new ImplementEmployeeDao();
    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;

    public EmployeeRegistration(){
    }

    public void getEmployeeInfo() throws SQLException {
        System.out.println("Please enter employees first name: ");
        firstName = scan.nextLine();

        System.out.println("Please enter employees last name: ");
        lastName = scan.nextLine();

        boolean emailFlag = false;
        email = "";
        while(!emailFlag){
            System.out.println("Please enter the employees email: ");
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
                System.out.println("I'm sorry, but that is an invalid email entry. Please " +
                        "try again.");
            }
        }
        boolean phoneFlag = false;
        phone = "";
        while(!phoneFlag){
            System.out.println("Please enter the employees phone number, including area code: ");
            String phoneNumberCheck = scan.nextLine();
            String regex = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNumberCheck);
            if(m.matches()){
                phone = phoneNumberCheck;
                phoneFlag = true;
            }
            else{
                System.out.println("I'm sorry, but that is an invalid phone number entry. " +
                        "Please try again.");
            }
        }

        Employee employee = new Employee(id, lastName, firstName, email, phone);
        addEmployee(employee);
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addEmployee(Employee employee) {
        return EMPLOYEE_DAO.save(employee);
    }
}
