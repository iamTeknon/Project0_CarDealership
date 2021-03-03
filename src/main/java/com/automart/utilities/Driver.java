package com.automart.utilities;

import com.automart.ui.SignIn;

import java.util.Scanner;

public class Driver {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {

        SignIn si = new SignIn();
        si.signInOptions();

    }
}
