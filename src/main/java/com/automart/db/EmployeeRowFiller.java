package com.automart.db;

import com.automart.ui.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EmployeeRowFiller {
    private final String url = "jdbc:postgresql://enterprise.cxovyplivamc.us-east-2.rds.amazonaws.com:5432/myDB";
    private final String user = "postgres";
    private final String password = "postgres";

    // Primary key column is auto-filled with auto-incrementation
    private static final String INSERT_EMPLOYEES_SQL = "INSERT INTO employees" +
            " (last_name, first_name, phone_number, email) VALUES" +
            " (?, ?, ?, ?);";

    User u = new User();

    public void insertRecord(String firstName, String lastName, String phoneNumber,
                             String email) throws SQLException {
        System.out.println(INSERT_EMPLOYEES_SQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEES_SQL)) {
            preparedStatement.setString(1, lastName);
            preparedStatement.setString(2, firstName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);

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
