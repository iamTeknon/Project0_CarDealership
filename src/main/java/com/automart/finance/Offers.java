package com.automart.finance;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.registry.CustomerCarRegistration;
import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class Offers {
    private static final Scanner scan = new Scanner(System.in);
    private static final AutomartCar ac = new AutomartCar();
    private static final SignInPad sip = new SignInPad();
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();

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

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static AutomartCar getAutomartCar(int id) throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.get(id);
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }
}
