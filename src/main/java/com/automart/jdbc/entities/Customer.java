package com.automart.jdbc.entities;

public class Customer {
    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;
    private String address;
    private String city;
    private String state;
    private String zip;

    public Customer(){
    }
    public Customer(String lastName, String firstName, String email,
                    String phone, String address,
                    String city, String state, String zip) {
        this.firstName = lastName;
        this.lastName = firstName;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZip() {
        return zip;
    }

    @Override
    public String toString() {
        return "Customer{"
                + "id = " + id
                + "firstName = " + firstName
                + ", lastName = " + lastName
                + "email = " + email
                + ", phone = " + phone
                + ", address = " + address
                + ", city = " + city
                + ", state = " + state
                + ", zip = " + zip
                + "}";

    }
}


