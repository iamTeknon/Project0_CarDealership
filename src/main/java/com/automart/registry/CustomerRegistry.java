package com.automart.registry;

import com.automart.db.CustomerRowFiller;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerRegistry {
    private static final Scanner scan = new Scanner(System.in);

    public CustomerRegistry(){
    }

    CustomerRowFiller rf = new CustomerRowFiller();

    public void getRegistrationInfo() throws SQLException {
        // TODO: Need to create a register method for this and then just call it
        System.out.println("Howdy! Welcome to Automart! Please enter your first name: ");
        String firstName = scan.nextLine();
        System.out.println("Enter your last name: ");
        String lastName = scan.nextLine();
        boolean phoneNumberFlag = false;
        String phoneNumber = "";
        while(!phoneNumberFlag){
            System.out.println("Enter your phone number, including area code: ");
            String phoneNumberCheck = scan.nextLine();
            String regex = "^\\D?(\\d{3})\\D?\\D?(\\d{3})\\D?(\\d{4})$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phoneNumberCheck);
            if(m.matches()){
                phoneNumber = phoneNumberCheck;
                phoneNumberFlag = true;
            }
            else{
                System.out.println("Invalid phone number entry");
            }
        }
        boolean emailFlag = false;
        String email = "";
        while(!emailFlag){
            System.out.println("Enter your email: ");
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
        System.out.println("Enter your street address: ");
        String address = scan.nextLine();
        System.out.println("Enter your city: ");
        String city = scan.nextLine();
        boolean stateFlag = false;
        String state = "";
        while(!stateFlag){
            System.out.println("Enter your state: ");
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
                System.out.println("Invalid state entry");
            }
        }
        boolean zipFlag = false;
        String zip = "";
        while(!zipFlag){
            System.out.println("Enter your zip code: ");
            String zipCheck = scan.nextLine();
            String regex = "^\\d{5}(-\\d{4})?$";
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(zipCheck);
            if(m.matches()){
                zip = zipCheck;
                zipFlag = true;
            }
            else{
                System.out.println("Invalid zipcode entry");
            }
        }
        boolean passwordFlag = false;
        String password = "";
        while(!passwordFlag){
            System.out.println("Please enter a password that has at least 6 characters " +
                    "and at most 20 characters, including at least 1 capital letter and 1 number," +
                    "with no spaces: ");
            String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,20}$";
            String passwordCheck = scan.nextLine();
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(passwordCheck);

            // TODO: Need to figure out why it says invalid password as soon as it gets here
            if(!m.matches()){
                System.out.println("Invalid password entry");
            }
            else{
                password = passwordCheck;
                passwordFlag = true;
            }
        }
        rf.insertRecord(lastName, firstName, phoneNumber, email, address, city, state, zip, password);
    }
}
