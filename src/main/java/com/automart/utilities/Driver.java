package com.automart.utilities;

import com.automart.db.CreateTable;

import java.sql.SQLException;
import java.util.Scanner;

public class Driver {

    Scanner scan = new Scanner(System.in);

    public static void main(String[] args) throws SQLException {

//        SignIn si = new SignIn();
//        si.signInOptions();
        CreateTable createTableExample = new CreateTable();
        createTableExample.connection();

    }
}
