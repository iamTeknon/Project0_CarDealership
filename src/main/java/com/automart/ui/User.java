package com.automart.ui;


import com.automart.utilities.MyBinaryTree;

import java.util.Scanner;
// Regex code borrowed from https://owasp.org/www-community/OWASP_Validation_Regex_Repository
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {
    /*
        TODO: CREATE FIELDS
    */
    // For log in
    private String customerFirstName;
    private String customerLastName;
    private String customerPhoneNumber;
    private String customerEmail;
    private int keyNumber = 0;

    // Additional info for registration
    private String customerStreetAddress;
    private String customerAddressCity;
    private String customerAddressState;
    private String customerAddressZip;
    private String password;

    /*
        TODO: CREATE CONSTRUCTOR(s)
    */
    public User(){
    }

    Scanner scan = new Scanner(System.in);
    MyBinaryTree bTree = new MyBinaryTree();

    public void logInCheck(){
        System.out.println("Enter 'r' to register for an account or 'l' to log in: ");
        String initialOption = scan.nextLine();
        if(initialOption.equalsIgnoreCase("r")){
            // TODO: need to store this info in db
            setCustomerFirstName();
            setCustomerLastName();
            setCustomerPhoneNumber();
            setCustomerEmail();
            setCustomerStreetAddress();
            setCustomerAddressCity();
            setCustomerAddressState();
            setCustomerAddressZip();
            setPassword();
            setKeyNumber();
            //bTree.addNode(this.keyNumber, this.customerFirstName);
            logInCheck();
        }else if(initialOption.equalsIgnoreCase("l")){
            System.out.println("Please enter your email: ");
            String logInEmail = scan.nextLine();
            System.out.println("Please enter your password: ");
            String logInPassword = scan.nextLine();
            // TODO: Need to match entries to db info
            // TODO: If no match then have them try 3 more times max
        }
    }

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
        String regex = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(phoneNumber);
        if(m.matches()){
            this.customerPhoneNumber = phoneNumber;
        }
        else{
            System.out.println("Invalid phone number entry");
            setCustomerPhoneNumber();
        }
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail() {
        System.out.println("Please enter your email address: ");
        String email = scan.nextLine();
        String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+" +
                "[a-zA-Z]{2,7}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(email);
        if(m.matches()){
            this.customerEmail = email;
        }
        else{
            System.out.println("Invalid email entry");
            setCustomerEmail();
        }
    }

    public String getCustomerStreetAddress() {
        return customerStreetAddress;
    }

    public void setCustomerStreetAddress() {
        System.out.println("Please enter your street address: ");
        String address = scan.nextLine();
        this.customerStreetAddress = address;
    }

    public String getCustomerAddressCity() {
        return customerAddressCity;
    }

    public void setCustomerAddressCity() {
        System.out.println("Please enter the city you live in: ");
        String city = scan.nextLine();
        this.customerAddressCity = city;
    }

    public String getCustomerAddressState() {
        return customerAddressState;
    }

    public void setCustomerAddressState() {
        System.out.println("Please enter the state you live in (2 letter abbreviation only): ");
        String state = scan.nextLine();
        String regex = "^(AE|AL|AK|AP|AS|AZ|AR|CA|CO|CT|DE|DC|FM|FL|GA|GU|HI|ID" +
                "|IL|IN|IA|KS|KY|LA|ME|MH|MD|MA|MI|MN|MS|MO|MP|MT|NE|NV|NH|NJ|NM" +
                "|NY|NC|ND|OH|OK|OR|PW|PA|PR|RI|SC|SD|TN|TX|UT|VT|VI|VA|WA|WV|WI|WY" +
                "ae|al|ak|ap|as|az|ar|ca|co|ct|de|dc|fm|fl|ga|gu|hi|id" +
                "|il|tn|ia|ks|ky|la|me|mh|md|ma|mi|mn|ms|mo|mp|mt|ne|nv|nh|nj|nm" +
                "|ny|nc|nd|oh|ok|or|pw|pa|pr|ri|sc|sd|tn|tx|ut|vt|vi|va|wa|wv|wi|wy)$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(state);
        if(m.matches()){
            this.customerAddressState= state;
        }
        else{
            System.out.println("Invalid state entry");
            setCustomerAddressState();
        }
    }

    public String getCustomerAddressZip() {
        return customerAddressZip;
    }

    public void setCustomerAddressZip() {
        System.out.println("Please enter your zip code: ");
        String zip = scan.nextLine();
        String regex = "^\\d{5}(-\\d{4})?$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(zip);
        if(m.matches()){
            this.customerAddressZip= zip;
        }
        else{
            System.out.println("Invalid zipcode entry");
            setCustomerAddressZip();
        }
    }

    public void setKeyNumber() {
        this.keyNumber += 1;
        // test to make sure it's incrementing
        System.out.println(keyNumber);
    }

    public int getKeyNumber(){
        return this.keyNumber;
    }

    public void setPassword(){
        System.out.println("Please enter a password that has at least 6 characters " +
                "and at most 20 characters, including at least 1 capital letter and 1 number," +
                "with no spaces: ");
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
        String password = scan.nextLine();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(password);

        // TODO: Need to figure out why it says invalid password as soon as it gets here
        if(!m.matches()){
            System.out.println("Invalid password entry");
            setPassword();
        }
        else{
            this.password = password;
        }
    }

    public String getPassword(){
        return this.password;
    }

    /*
        TODO: CREATE METHODS
            employeeLogIn
            customerLogIn
            customerAccountRegistration
     */
}
