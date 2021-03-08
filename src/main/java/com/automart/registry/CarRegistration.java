package com.automart.registry;

import com.automart.jdbc.crud.Dao;
import com.automart.jdbc.crud.ImplementAutomartCarDao;
import com.automart.jdbc.entities.AutomartCar;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Scanner;

public class CarRegistration {
    private static final Scanner scan = new Scanner(System.in);
    private static final Dao<AutomartCar, Integer> AUTOMART_CAR_DAO = new ImplementAutomartCarDao();
    private int id;
    private int year;
    private String make;
    private String model;
    private String color;
    private BigDecimal price;

    public CarRegistration(){
    }

    public void getEmployeeInfo() throws SQLException {
        System.out.println("What year model is the car? ");
        year = scan.nextInt();
        scan.nextLine();

        System.out.println("What is the make? ");
        make = scan.nextLine();

        System.out.println("What is the model? ");
        model = scan.nextLine();

        System.out.println("What color is the car? ");
        color = scan.nextLine();

        System.out.println("What is the sale price of the car? ");
        price = scan.nextBigDecimal();
    }

    AutomartCar ac = new AutomartCar(id, year, make, model, color, price);

    // The following code was borrowed from Hiram Kamau and modified for this project
    // https://stackabuse.com/working-with-postgresql-in-java/
    public static Optional<Integer> addAutomartCar(AutomartCar car) {
        return AUTOMART_CAR_DAO.save(car);
    }
}
