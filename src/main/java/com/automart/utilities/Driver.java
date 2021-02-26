package com.automart.utilities;

import com.automart.ui.User;

import java.util.Scanner;

public class Driver {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        User user = new User();
        user.setCustomerFirstName();
        user.setCustomerLastName();
        user.setCustomerPhoneNumber();
        user.setCustomerEmail();

    }
}
