package com.automart.db.fill;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRowFiller {
    private final String url = "jdbc:postgresql://enterprise2102.cxovyplivamc.us-east-2.rds.amazonaws.com:5432/aws";
    private final String user = "postgres";
    private final String password = "postgres";

    // Primary key column is auto-filled with auto-incrementation
    private static final String INSERT_CUSTOMERS_SQL = "INSERT INTO project0.customers_test1" +
            " (last_name, first_name, phone_number, email, street_address, city, state, zip_code, password) VALUES" +
            " (?, ?, ?, ?, ?, ?, ?, ?, ?);";

    // TODO: Need to created instantiation in Driver class and leave commented out
    public void insertRecord(String lastName, String firstName, String phoneNumber, String email,
                             String address, String city, String state, String zip, String customerPassword) throws SQLException {
        System.out.println(INSERT_CUSTOMERS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMERS_SQL)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);
            preparedStatement.setString(5, address);
            preparedStatement.setString(6, city);
            preparedStatement.setString(7, state);
            preparedStatement.setString(8, zip);
            preparedStatement.setString(9, customerPassword);

            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            // print SQL exception information
            printSQLException(e);
        }

        // Step 4: try-with-resource statement will auto close the connection.
    }

    public static void printSQLException(SQLException ex) {
        for (Throwable e: ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
