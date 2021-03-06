package com.automart.db.create;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CustomerTableMaker {
    private final String url = "jdbc:postgresql://enterprise2102.cxovyplivamc.us-east-2.rds.amazonaws.com:5432/aws";
    private final String user = "postgres";
    private final String password = "postgres";

    private static final String createTableSQL = "CREATE TABLE project0.customers_test1 " +
            "(CUSTOMER_ID SERIAL PRIMARY KEY," +
            " LAST_NAME TEXT, " +
            " FIRST_NAME TEXT, " +
            " PHONE_NUMBER VARCHAR(20), " +
            " EMAIL VARCHAR(50) UNIQUE, " +
            " STREET_ADDRESS VARCHAR(50), " +
            " CITY TEXT, " +
            " STATE TEXT, " +
            " ZIP_CODE VARCHAR(10), " +
            " PASSWORD VARCHAR(20))";

    public static void main(String[] args) throws SQLException {

        CustomerTableMaker ctm = new CustomerTableMaker();
        ctm.connection();

    }

    public void connection() throws SQLException {

        System.out.println(createTableSQL);
        // Step 1: Establishing a Connection
        try (Connection connection = DriverManager.getConnection(url, user, password);

             // Step 2:Create a statement using connection object
             Statement statement = connection.createStatement();) {

            // Step 3: Execute the query or update query
            statement.execute(createTableSQL);
        } catch (SQLException e) {

            // print SQL exception information
            printSQLException(e);
        }
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

