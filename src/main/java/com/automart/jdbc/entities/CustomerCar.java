package com.automart.jdbc.entities;

public class CustomerCar {
    private int car_id;
    private int customer_id;
    private String year;
    private String make;
    private String model;
    private String color;
    private double monthlyPayment;
    private double balance;

    public CustomerCar(int car_id, int customer_id, String year, String make, String model, String color,
                       double monthlyPayment, double balance) {
        this.car_id = car_id;
        this.customer_id = customer_id;
        this.year = year;
        this.make = make;
        this.model = model;
        this.color = color;
        this.monthlyPayment = monthlyPayment;
        this.balance = balance;
    }

    public CustomerCar() {
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getCar_id() {
        return car_id;
    }

    public void setCar_id(int car_id) {
        this.car_id = car_id;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
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

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
