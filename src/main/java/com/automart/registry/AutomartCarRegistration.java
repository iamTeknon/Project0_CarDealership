package com.automart.registry;

import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
import com.automart.jdbc.entities.AutomartCar;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class AutomartCarRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private int id;
    private int year;
    private String make;
    private String model;
    private String color;
    private BigDecimal price;

    public AutomartCarRegistration(){
    }

    public void getAutomartCarInfo() throws SQLException {
        System.out.println("What year model is the vehicle? ");
        year = scan.nextInt();
        scan.nextLine();

        System.out.println("What is the make? ");
        make = scan.nextLine();

        System.out.println("What is the model? ");
        model = scan.nextLine();

        System.out.println("What color is the vehicle? ");
        color = scan.nextLine();

        System.out.println("What is the sale price of the vehicle? ");
        price = scan.nextBigDecimal();

        AutomartCar ac = new AutomartCar(id, year, make, model, color, price);
        addAutomartCar(ac);
    }

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addAutomartCar(AutomartCar car) {
        return AUTOMART_CAR_DAO.save(car);
    }
}
