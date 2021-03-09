package com.automart.jdbc.crud;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementOffersDao;
import com.automart.jdbc.entities.Offers;
import com.automart.ui.SignInPad;

import java.util.Optional;
import java.util.Scanner;

public class UpdateOffers {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<Offers, Integer> OFFERS_CAR_DAO = new ImplementOffersDao();
    private static final SignInPad sip = new SignInPad();
    private static final Offers o = new Offers();



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
