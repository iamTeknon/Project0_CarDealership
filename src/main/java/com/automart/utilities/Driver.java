package com.automart.utilities;

import com.automart.ui.SignInPad;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

        SignInPad si = new SignInPad();
        si.signInOptions();



    }
}
