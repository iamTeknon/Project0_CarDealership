package com.automart.jdbc.crud;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.dao.Dao;
import com.automart.jdbc.dao.ImplementAutomartCarDao;
import com.automart.jdbc.entities.AutomartCar;
import com.automart.ui.SignInPad;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class UpdateAutomartCarInfo {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private static final SignInPad sip = new SignInPad();
    private static final AutomartCar ac = new AutomartCar();

    public UpdateAutomartCarInfo(){
    }

    public void updateCarInfo(int carId) throws SQLException, NonExistentEntityException {
        try{
            AutomartCar ac = getAutomartCar(carId);
        }catch (NonExistentEntityException ex){
            ex.printStackTrace();
            System.out.println("That car is not in the database. Please make " +
                    "sure you entered the correct Automart car id number.");
        }
        System.out.println("Please enter " +
            "'u' to update the vehicle information in the database, " +
            "'r' to remove the vehicle from the database," +
            " or 'x' to exit: ");
        String updateOption = scan.nextLine();
        switch (updateOption) {
            case "u":
                System.out.println("Enter the year of the vehicle: ");
                int updatedYear = scan.nextInt();
                scan.nextLine();
                ac.setYear(updatedYear);
                System.out.println("Enter the make of the vehicle: ");
                String updatedMake = scan.nextLine();
                ac.setMake(updatedMake);
                System.out.println("Enter the model of the vehicle: ");
                String updateModel = scan.nextLine();
                ac.setModel(updateModel);
                System.out.println("Enter the color of the vehicle: ");
                String updateColor = scan.nextLine();
                ac.setColor(updateColor);
                System.out.println("Enter the new asking price: ");
                BigDecimal newPrice = scan.nextBigDecimal();
                scan.nextLine();
                ac.setPrice(newPrice);
                ac.setId(carId);
                updateAutomartCar(ac);
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
          case "r":
                ac.setId(carId);
                deleteAutomartCar(ac);
               System.out.println("You will now be redirected to the sign in options menu.");
                break;
            case "x":
                System.out.println("You will now be redirected to the sign in options menu.");
                break;
            default:
                break;
        }
        sip.signInOptions();
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static AutomartCar getAutomartCar(int id) throws NonExistentEntityException {
        Optional<AutomartCar> automartCar = AUTOMART_CAR_DAO.get(id);
        return automartCar.orElseThrow(NonExistentCustomerException::new);
    }

    public static void updateAutomartCar(AutomartCar car) {
        AUTOMART_CAR_DAO.update(car);
    }

    public static void deleteAutomartCar(AutomartCar car) {
        AUTOMART_CAR_DAO.delete(car);
    }

}
