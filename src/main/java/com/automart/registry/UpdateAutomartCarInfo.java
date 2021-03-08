package com.automart.registry;

import com.automart.exceptions.NonExistentCustomerException;
import com.automart.exceptions.NonExistentEntityException;
import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
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
        boolean updateFlag = false;
        while (!updateFlag) {
            System.out.println("Please enter " +
                "'y' to update year of vehicle, " +
                "'m' to update vehicle make, " +
                "'o' to update vehicle model, " +
                "'c' to update vehicle color, " +
                "'p' to update vehicle price, " +
                "'d' to delete vehicle, " +
                "or 'x' to exit: ");
            String updateOption = scan.nextLine();
            switch (updateOption) {
                case "y":
                    System.out.println("Enter the year of the vehicle: ");
                    int updatedYear = scan.nextInt();
                    scan.nextLine();
                    ac.setYear(updatedYear);
                    ac.setId(carId);
                    updateAutomartCar(ac);
                    break;
                case "m":
                    System.out.println("Enter the make of the vehicle: ");
                    String updatedMake = scan.nextLine();
                    ac.setMake(updatedMake);
                    ac.setId(carId);
                    updateAutomartCar(ac);
                    break;
                case "o":
                    System.out.println("Enter the model of the vehicle: ");
                    String updateModel = scan.nextLine();
                    ac.setModel(updateModel);
                    ac.setId(carId);
                    updateAutomartCar(ac);
                    break;
                case "c":
                    System.out.println("Enter the color of the vehicle: ");
                    String updateColor = scan.nextLine();
                    ac.setColor(updateColor);
                    ac.setId(carId);
                    updateAutomartCar(ac);
                    break;
                case "p":
                    System.out.println("Enter the new asking price: ");
                    BigDecimal newPrice = scan.nextBigDecimal();
                    scan.nextLine();
                    ac.setPrice(newPrice);
                    ac.setId(carId);
                    updateAutomartCar(ac);
                    break;
                case "d":
                    ac.setId(carId);
                    deleteAutomartCar(ac);
                    break;
                case "x":
                    updateFlag = true;
                    break;
                default:
                    break;
            }
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
