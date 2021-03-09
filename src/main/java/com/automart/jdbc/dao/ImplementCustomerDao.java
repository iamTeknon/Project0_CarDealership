package com.automart.jdbc.dao;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.Customer;
import java.sql.*;
import java.util.Objects;
import java.util.Optional;

// The following code was borrowed from Hiram Kamau and modified for this project
// https://stackabuse.com/working-with-postgresql-in-java/
public class ImplementCustomerDao implements Dao<Customer, Integer> {
    private final Optional<Connection> connection;

    public ImplementCustomerDao() {
        this.connection = AwsConnection.getConnection();
    }

    @Override
    public Optional<Integer> save(Customer customer) {
        String message = "The customer to be added should not be null";
        Customer nonNullCustomer = Objects.requireNonNull(customer, message);
        String sql = "INSERT INTO "
                + "project0.customers(last_name, first_name, email, phone_number, " +
                "street_address, city, state, zip_code) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, nonNullCustomer.getLastName());
                statement.setString(2, nonNullCustomer.getFirstName());
                statement.setString(3, nonNullCustomer.getEmail());
                statement.setString(4, nonNullCustomer.getPhone());
                statement.setString(5, nonNullCustomer.getAddress());
                statement.setString(6, nonNullCustomer.getCity());
                statement.setString(7, nonNullCustomer.getState());
                statement.setString(8, nonNullCustomer.getZip());

                int numberOfInsertedRows = statement.executeUpdate();

                // Retrieve the auto-generated id
                if (numberOfInsertedRows > 0) {
                    try (ResultSet resultSet = statement.getGeneratedKeys()) {
                        if (resultSet.next()) {
                            generatedId = Optional.of(resultSet.getInt(1));
                        }
                    }
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return generatedId;
        });
    }

    public Optional<Customer> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Customer> customer = Optional.empty();
            String sql = "SELECT customer_id, last_name, first_name, email, phone_number, street_address," +
                    " city, state, zip_code FROM project0.customers WHERE customer_id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int customer_id = resultSet.getInt("customer_id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone_number");
                    String address = resultSet.getString("street_address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zip  = resultSet.getString("zip_code");
                    System.out.println(customer_id + "  " +  lastName + ", " + firstName + "  " + email
                            + "  " + phone + "  " + address + "  " + city + "  " + state + "  " + zip);

                    customer = Optional.of(
                            new Customer(id, lastName, firstName, email, phone,
                                    address, city, state, zip));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return customer;
        });
    }

    public Optional<Customer> getEverything() {
        return connection.flatMap(conn -> {
            Optional<Customer> customer = Optional.empty();
            String sql = "SELECT * FROM project0.customers";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int customer_id = resultSet.getInt("customer_id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone_number");
                    String address = resultSet.getString("street_address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zip  = resultSet.getString("zip_code");
                    System.out.println(customer_id + "  " +  lastName + ", " + firstName + "  " + email
                            + "  " + phone + "  " + address + "  " + city + "  " + state + "  " + zip);

                    customer = Optional.of(
                            new Customer(customer_id, lastName, firstName, email, phone,
                                    address, city, state, zip));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return customer;
        });
    }

    public void update(Customer customer) {
        String message = "The customer to be updated should not be null";
        Customer nonNullCustomer = Objects.requireNonNull(customer, message);
        String sql = "UPDATE project0.customers "
                + "SET "
                + "last_name = ?, "
                + "first_name = ?, "
                + "email = ?, "
                + "phone_number = ?, "
                + "street_address = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zip_code = ? "
                + "WHERE "
                + "customer_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullCustomer.getLastName());
                statement.setString(2, nonNullCustomer.getFirstName());
                statement.setString(3, nonNullCustomer.getEmail());
                statement.setString(4, nonNullCustomer.getPhone());
                statement.setString(5, nonNullCustomer.getAddress());
                statement.setString(6, nonNullCustomer.getCity());
                statement.setString(7, nonNullCustomer.getState());
                statement.setString(8, nonNullCustomer.getZip());
                statement.setInt(9, nonNullCustomer.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void delete(Customer customer) {
        String message = "The customer to be deleted should not be null";
        Customer nonNullCustomer = Objects.requireNonNull(customer, message);
        String sql = "DELETE FROM project0.customers WHERE customer_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullCustomer.getId());

                int numberOfDeletedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}
