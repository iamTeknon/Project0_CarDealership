package com.automart.jdbc.entities;

public class Employee {
    private Integer id;
    private String lastName;
    private String firstName;
    private String email;
    private String phone;

    public Employee() {
    }

    public Employee(Integer id, String firstName, String lastName, String email,
                    String phone) {
        this.firstName = lastName;
        this.lastName = firstName;
        this.email = email;
        this.phone = phone;
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

    @Override
    public String toString() {
        return "Customer["
                + "id = " + id
                + "lastName = " + firstName
                + ", firstName = " + lastName
                + ", email = " + email
                + ", phone = " + phone
                + "]";
    }
}


