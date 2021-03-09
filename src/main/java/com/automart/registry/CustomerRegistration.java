package com.automart.registry;

import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementCustomerDao;
import com.automart.jdbc.entities.Customer;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

// Regex code borrowed from https://owasp.org/www-community/OWASP_Validation_Regex_Repository
public class CustomerRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private String lastName;
    private String firstName;
    private String email;
    private String phoneNumber;
    private String address;
    private String city;
    private String state;
    private String zip;
    private int id;

    public CustomerRegistration(){
    }

    public void getCustomerInfo() throws SQLException {

        System.out.println("Howdy! Welcome to Automart! \nPlease enter your first name: ");
        firstName = scan.nextLine();

        System.out.println("Please enter your last name: ");
        lastName = scan.nextLine();

        boolean phoneNumberFlag = false;
        phoneNumber = "";
        while(!phoneNumberFlag){
            System.out.println("Please enter your phone number, including area code: ");
            String phoneNumberCheck = scan.nextLine();
            String regex = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNumberCheck);
            if(m.matches()){
                phoneNumber = phoneNumberCheck;
                phoneNumberFlag = true;
            }
            else{
                System.out.println("I'm sorry, but that is an invalid phone number entry. Please " +
                        "try again.");
            }
        }

        boolean emailFlag = false;
        email = "";
        while(!emailFlag){
            System.out.println("Please enter your email: ");
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
                System.out.println("I'm sorry, but that is an invalid email entry. Please try again.");
            }
        }

        System.out.println("Please enter your street address: ");
        address = scan.nextLine();

        System.out.println("Enter your city: ");
        city = scan.nextLine();

        boolean stateFlag = false;
        state = "";
        while(!stateFlag){
            System.out.println("Please enter your state: ");
            String stateCheck = scan.nextLine();
            String regex = "^(AE|AL|AK|AP|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID" +
                    "|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MP|MT|NE|NV|NH|NJ|NM" +
                    "|NY|NC|ND|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY" +
                    "ae|al|ak|ap|as|az|ar|ca|co|ct|de|dc|fm|fl|ga|gu|hi|id" +
                    "|il|tn|ia|ks|ky|la|me|mh|md|ma|mi|mn|ms|mo|mp|mt|ne|nv|nh|nj|nm" +
                    "|ny|nc|nd|oh|ok|or|pw|pa|pr|ri|sc|sd|tn|tx|ut|vt|vi|va|wa|wv|wi|wy)$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(stateCheck);
            if(m.matches()){
                state = stateCheck;
                stateFlag = true;
            }
            else{
                System.out.println("I'm sorry, but that is an invalid state entry. Please make sure " +
                        "you enter the state abbreviation only.");
            }
        }

        boolean zipFlag = false;
        this.zip = "";
        while(!zipFlag){
            System.out.println("Please enter your zip code: ");
            String zipCheck = scan.nextLine();
            String regex = "^\\d{5}(-\\d{4})?$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(zipCheck);
            if(m.matches()){
                zip = zipCheck;
                zipFlag = true;
            }
            else{
                System.out.println("I'm sorry, but that is an invalid zipcode entry. Please " +
                        "try again.");
            }
        }

        Customer c = new Customer(id, lastName, firstName, email, phoneNumber, address, city, state, zip);
        addCustomer(c);
    }

    // The following method was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addCustomer(Customer customer) {
        return CUSTOMER_DAO.save(customer);
    }
}
