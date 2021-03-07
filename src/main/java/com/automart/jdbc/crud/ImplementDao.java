package com.automart.jdbc.crud;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

// The following code was borrowed from Hiram Kamau and modified for this project
// https://stackabuse.com/working-with-postgresql-in-java/
public class ImplementDao implements Dao<Customer, Integer>{
    private final Optional<Connection> connection;

    public ImplementDao() {
        this.connection = AwsConnection.getConnection();
    }

    @Override
    public Optional<Integer> save(Customer customer) {
        String message = "The customer to be added should not be null";
        Customer nonNullCustomer = Objects.requireNonNull(customer, message);
        String sql = "INSERT INTO "
                + "project0.customers_test1(last_name, first_name, phone_number, email, " +
                "street_address, city, state, zip_code) "
                + "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {

                statement.setString(1, nonNullCustomer.getFirstName());
                statement.setString(2, nonNullCustomer.getLastName());
                statement.setString(3, nonNullCustomer.getPhone());
                statement.setString(4, nonNullCustomer.getEmail());
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
            //String sql = "SELECT * FROM project0.customers_test1 WHERE customer_id = " + id;
            String sql = "SELECT customer_id, last_name, first_name, phone_number, email, street_address," +
                    " city, state, zip_code FROM project0.customers_test1 WHERE customer_id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int customer_id = resultSet.getInt("customer_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone_number");
                    String address = resultSet.getString("street_address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zip  = resultSet.getString("zip_code");
                    System.out.println(customer_id + ", " + lastName + ", " + firstName + ", " + phone + ", "
                            + email + ", " + address + ", " + city + ", " + state + ", " + zip);
                    customer = Optional.of(
                            new Customer(customer_id, lastName, firstName, phone, email,
                                    address, city, state, zip));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return customer;
        });
    }

    public Collection<Customer> getAll() {
        Collection<Customer> customers = new ArrayList<>();
        String sql = "SELECT * FROM project0.customers_test1";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int customer_id = resultSet.getInt("customer_id");
                    String firstName = resultSet.getString("first_name");
                    String lastName = resultSet.getString("last_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone_number");
                    String address = resultSet.getString("street_address");
                    String city = resultSet.getString("city");
                    String state = resultSet.getString("state");
                    String zip  = resultSet.getString("zip_code");
//                    System.out.println(customer_id + ", " + lastName + ", " + firstName + ", " + phone + ", "
//                            + email + ", " + address + ", " + city + ", " + state + ", " + zip);

                    Customer customer = new Customer(customer_id, firstName, lastName, phone, email,
                            address, city, state, zip);

                    customers.add(customer);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        return customers;
    }

    public void update(Customer customer) {
        String message = "The customer to be updated should not be null";
        Customer nonNullCustomer = Objects.requireNonNull(customer, message);
        String sql = "UPDATE project0.customers_test1 "
                + "SET "
                + "last_name = ?, "
                + "first_name = ?, "
                + "phone_number = ?, "
                + "email = ?, "
                + "street_address = ?, "
                + "city = ?, "
                + "state = ?, "
                + "zip_code = ? "
                + "WHERE "
                + "customer_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setString(1, nonNullCustomer.getFirstName());
                statement.setString(2, nonNullCustomer.getLastName());
                statement.setString(3, nonNullCustomer.getPhone());
                statement.setString(4, nonNullCustomer.getEmail());
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
        String sql = "DELETE FROM project0.customers_test1 WHERE customer_id = ?";

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
