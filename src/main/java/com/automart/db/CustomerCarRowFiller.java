package com.automart.db;

import com.automart.ui.User;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerCarRowFiller {
    private final String url = "jdbc:postgresql://enterprise.cxovyplivamc.us-east-2.rds.amazonaws.com:5432/myDB";
    private final String user = "postgres";
    private final String password = "postgres";

    // Primary key column is auto-filled with auto-incrementation
    private static final String INSERT_CARS_SQL = "INSERT INTO customer_cars" +
            " (year, make, model, color, monthly_payment, balance) VALUES" +
            " (?, ?, ?, ?, ?, ?);";

    User u = new User();

    public void insertRecord(int year, String make, String model, String color,
                             BigDecimal monthly_payment, BigDecimal balance) throws SQLException {
        System.out.println(INSERT_CARS_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CARS_SQL)) {
            preparedStatement.setInt(1, year);
            preparedStatement.setString(2, make);
            preparedStatement.setString(3, model);
            preparedStatement.setString(4, color);
            preparedStatement.setBigDecimal(5, monthly_payment);
            preparedStatement.setBigDecimal(6, balance);

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
