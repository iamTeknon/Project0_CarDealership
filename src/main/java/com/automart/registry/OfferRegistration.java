package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementAutomartCarDao;
import com.automart.jdbc.dao.ImplementCustomerDao;
import com.automart.jdbc.dao.ImplementOffersDao;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.jdbc.entities.Customer;
import com.automart.jdbc.entities.Offers;
import com.automart.ui.SignInPad;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class OfferRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final SignInPad sip = new SignInPad();
    private static final Dao<Offers, Integer> OFFERS_CAR_DAO = new ImplementOffersDao();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private static final Dao<Customer, Integer> CUSTOMER_DAO = new ImplementCustomerDao();
    private int offerId;
    private String verdict;
    private int customerId;
    private int carId;
    private BigDecimal offer;

    public OfferRegistration(String verdict){
        this.verdict = verdict;
    }

    public void getOfferInfo() throws SQLException, NonExistentEntityException {
        System.out.println("Please enter your customer ID: ");
        this.customerId = scan.nextInt();
        scan.nextLine();

        try{
            Customer c = getCustomer(customerId);
        }catch(NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("I'm sorry, but you are not in our database. Please make sure " +
                    "you have entered the correct customer id number.");
            sip.signInOptions();
        }

        System.out.println("Please enter the Automart car id number for the vehicle you " +
                "would like to make an offer on: ");
        this.carId = scan.nextInt();
        scan.nextLine();

        try{
            AutomartCar ac = getAutomartCar(carId);
        }catch (NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("I'm sorry, but that car is not in our database. Please make " +
                    "sure you entered the correct Automart car id number.");
        }

        System.out.println("What is your offer for this vehicle? ");
        this.offer = scan.nextBigDecimal();
        scan.nextLine();
        System.out.println("Please sit tight while our representatives review your offer! Someone " +
                "will contact you soon!");

        Offers o = new Offers(offerId, customerId, carId, offer, verdict);
        addOffers(o);
    }


    // The following methods are borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addOffers(Offers offer) {
        return OFFERS_CAR_DAO.save(offer);
    }

    public static AutomartCar getAutomartCar(int id) throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.get(id);
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }

    public static Customer getCustomer(int id) throws NonExistentEntityException {
        Optional<Customer> customer = CUSTOMER_DAO.get(id);
        return customer.orElseThrow(NonExistentCustomerException::new);
    }
}
