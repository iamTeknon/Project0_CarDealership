package com.automart.jdbc.crud;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementOffersDao;
import com.automart.jdbc.entities.Offers;
import com.automart.ui.SignInPad;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UpdateOffers {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Offers, Integer> OFFERS_CAR_DAO = new ImplementOffersDao();
    private static final SignInPad sip = new SignInPad();
    private static final Offers o = new Offers();
    private int id;

    public void updateOffers(int offerId, int customerId, int carId, BigDecimal offer) throws SQLException, NonExistentEntityException{
        System.out.println("Enter 'accept' to accept an offer or " +
                "'reject' to reject an offer, or 'r' to remove an offer from the database: ");
        String option = scan.nextLine();

        if(option.equalsIgnoreCase("accept")){
            o.setVerdict(option);
            o.setOffer(offer);
            o.setCustomerId(customerId);
            o.setVehicleId(carId);
            o.setOfferId(offerId);
            updateOffers(o);
            System.out.println("You will now be redirected to the sign in options menu.");
            sip.signInOptions();
        }
        else if(option.equalsIgnoreCase("reject")){
            o.setVerdict(option);
            o.setOffer(offer);
            o.setCustomerId(customerId);
            o.setVehicleId(carId);
            o.setOfferId(offerId);
            updateOffers(o);
            System.out.println("You will now be redirected to the sign in options menu.");
            sip.signInOptions();
        }
        else if(option.equalsIgnoreCase("r")){
            o.setOfferId(offerId);
            deleteOffers(o);
            sip.signInOptions();
        }    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Offers getOffers(int id) throws NonExistentEntityException {
        Optional<Offers> offer = OFFERS_CAR_DAO.get(id);
        return offer.orElseThrow(NonExistentCustomerException::new);
    }

    public static void updateOffers(Offers offer) {
        OFFERS_CAR_DAO.update(offer);
    }

    public static void deleteOffers(Offers offer) {
        OFFERS_CAR_DAO.delete(offer);
    }
}
