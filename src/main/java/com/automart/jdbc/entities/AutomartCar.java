package com.automart.jdbc.entities;

import java.math.BigDecimal;

public class AutomartCar {
    private int id;
    private int year;
    private String make;
    private String model;
    private String color;
    private BigDecimal price;

    public AutomartCar(){
    }

    public AutomartCar(int id, int year, String make, String model, String color, BigDecimal price){
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String toString(){
        return "AutomartCar["
            + "id = " + id
            + ", year = " + year
            + ", make = " + make
            + ", model = " + model
            + ", color = " + color
            + ", price = " + price
            +"]";

    }
}
