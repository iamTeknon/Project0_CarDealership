package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
import com.automart.jdbc.crud.ImplementCustomerCarDao;
import com.automart.jdbc.crud.ImplementCustomerDao;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.jdbc.entities.Customer;
import com.automart.jdbc.entities.CustomerCar;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class CustomerCarRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<CustomerCar, Integer> CUSTOMER_CAR_DAO = new ImplementCustomerCarDao();
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private static final SignInPad sip = new SignInPad();
//    private static final CustomerCar cr = new CustomerCar();
    private String email;
    private int year;
    private String make;
    private String model;
    private String color;
    private double monthlyPayment;
    private int car_id;

    public CustomerCarRegistration(){
    }

    public void getPurchasedCarInfo(int carId) throws SQLException, NonExistentEntityException {
        System.out.println("Please enter the customers ID: ");
        int customerId = scan.nextInt();
        scan.nextLine();
//        System.out.println("The current customer id in customer class is: " + cr.getCustomer_id());
//        cr.setCustomer_id(customerId);
//        System.out.println("The current customer id in customer class is: " + cr.getCustomer_id());
        try{
            Customer customer = getCustomer(customerId);
        }catch(NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("That customer is not in the database. Please make sure " +
                    "you have the correct customer id #.");
            sip.signInOptions();
        }
        try{
            AutomartCar car = getAutomartCar(carId);
        }catch(NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("That vehicle is not in the database. Please make sure " +
                    "you have the correct Automart car id number.");
            sip.signInOptions();
        }

        System.out.println("Enter the year of the purchased car: ");
        year = scan.nextInt();
        scan.nextLine();

        System.out.println("Enter the make of the purchased car: ");
        make = scan.nextLine();

        System.out.println("Enter the model of the purchased car: ");
        model = scan.nextLine();

        System.out.println("Enter the color of the purchased car: ");
        color = scan.nextLine();

        System.out.println("Enter the balance owed: ");
        double balanceOffer = scan.nextDouble();
        scan.nextLine();

        monthlyPayment = balanceOffer / 72;

//        cr.setCustomer_id(customerId);

        CustomerCar cc = new CustomerCar(car_id, customerId, year, make, model, color, monthlyPayment, balanceOffer);
        addCustomerCar(cc);
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addCustomerCar(CustomerCar car) {
        return CUSTOMER_CAR_DAO.save(car);
    }

    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }

    public static AutomartCar getAutomartCar(int id) throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.get(id);
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }

}
