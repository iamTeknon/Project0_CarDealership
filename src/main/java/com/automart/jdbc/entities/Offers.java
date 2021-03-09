package com.automart.jdbc.entities;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementAutomartCarDao;
import com.automart.registry.CustomerCarRegistration;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Offers {
    private static final Scanner scan = new Scanner(System.in);
    private static final SignInPad sip = new SignInPad();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private int offerId;
    private int customerId;
    private int vehicleId;
    private double offer;
    private String verdict;

    public Offers(int offerId, int customerId, int vehicleId, double offer, String verdict){
        this.offerId = offerId;
        this.customerId = customerId;
        this.vehicleId = vehicleId;
        this.offer = offer;
        this.verdict = verdict;
    }

    public Offers(){
    }

    public void makeOffer(int carId) throws SQLException, NonExistentEntityException {
        try{
            AutomartCar ac = getAutomartCar(carId);
        }catch (NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("That car is not in the database. Please make " +
                    "sure you entered the correct Automart car id number.");
        }
        boolean offerFlag = true;
        while(offerFlag){
            System.out.println("Please enter 'o' to make an offer or 'x' to exit:  ");
            String myOption = scan.nextLine();
            if(myOption.equalsIgnoreCase("o")){
                System.out.println("What is your offer? ");
                double myOffer = scan.nextDouble();
                scan.nextLine();
                // TODO: Create db that stores customer id, name, offer, and vehicle info
                System.out.println("Do you accept or reject this offer? 'a' to accept" +
                        " or 'r' to reject: ");
                String offerOption = scan.nextLine();
                if(offerOption.equalsIgnoreCase("a")){
                    System.out.println("CONGRATS! You are now going to be re-directed" +
                            " to finance to calculate your monthly payments!");
                    CustomerCarRegistration ccr = new CustomerCarRegistration();
                    ccr.getPurchasedCarInfo(carId);
                    offerFlag = false;
                    // TODO: Redirect to finance
                }else if(offerOption.equalsIgnoreCase("r")){
                    System.out.println("I'm sorry, but we cannot accept that offer. " +
                            "Please make a new offer.");
                }
            }else if(myOption.equalsIgnoreCase("x")){
                offerFlag = false;
                sip.signInOptions();
            }
        }
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static AutomartCar getAutomartCar(int id) throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.get(id);
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }
}
