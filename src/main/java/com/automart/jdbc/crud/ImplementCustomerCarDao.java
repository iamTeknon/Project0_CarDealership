package com.automart.jdbc.crud;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.CustomerCar;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class ImplementCustomerCarDao implements Dao<CustomerCar, Integer>{
    private final Optional<Connection> connection;

    public ImplementCustomerCarDao() {
    this.connection = AwsConnection.getConnection();
    }

    @Override
    public Optional<Integer> save(CustomerCar car) {
            String message = "The car to be added should not be null";
            CustomerCar nonNullCustomerCar = Objects.requireNonNull(car, message);

            String sql = "INSERT INTO "
            + "project0.customer_cars(customer_id, year, make, model, color, monthly_payments, balance) "
            + "VALUES(?, ?, ?, ?, ?, ?, ?)";

            return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setInt(1, nonNullCustomerCar.getCustomer_id());
                    statement.setString(2, nonNullCustomerCar.getYear());
                    statement.setString(3, nonNullCustomerCar.getMake());
                    statement.setString(4, nonNullCustomerCar.getModel());
                    statement.setString(5, nonNullCustomerCar.getColor());
                    statement.setDouble(6, nonNullCustomerCar.getMonthlyPayment());
                    statement.setDouble(7, nonNullCustomerCar.getBalance());


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

    public Optional<CustomerCar> get(int id) {
            return connection.flatMap(conn -> {
            Optional<CustomerCar> customerCar = Optional.empty();
            String sql = "SELECT car_id, customer_id, year, make, model, color, monthly_payments, balance" +
            " FROM project0.customer_cars WHERE customer_id = " + id;
            try (Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {

            if (resultSet.next()) {
            int car_id = resultSet.getInt("car_id");
            int customer_id = resultSet.getInt("customer_id");
            String year = resultSet.getString("year");
            String make = resultSet.getString("make");
            String model = resultSet.getString("model");
            String color = resultSet.getString("color");
            double monthly = resultSet.getDouble("monthly_payments");
            double balance = resultSet.getDouble("balance");

            System.out.println(car_id + "  " + customer_id + "  " +  year + "  " + make + "  " + model
            + "  " + color + "  " + monthly + "  "  + balance);

            customerCar = Optional.of(
            new CustomerCar(car_id, customer_id, year, make, model, color, monthly, balance));
            }
            } catch (SQLException ex) {
            ex.printStackTrace();
            }
            return customerCar;
            });
            }

        public Optional<CustomerCar> getEverything() {
                return connection.flatMap(conn -> {
                        Optional<CustomerCar> customerCar = Optional.empty();
                        String sql = "SELECT * FROM project0.customer_cars";
                        try (Statement statement = conn.createStatement();
                             ResultSet resultSet = statement.executeQuery(sql)) {

                                while (resultSet.next()) {
                                        int car_id = resultSet.getInt("car_id");
                                        int customer_id = resultSet.getInt("customer_id");
                                        String year = resultSet.getString("year");
                                        String make = resultSet.getString("make");
                                        String model = resultSet.getString("model");
                                        String color = resultSet.getString("color");
                                        double monthly = resultSet.getDouble("monthly_payments");
                                        double balance = resultSet.getDouble("balance");

                                        System.out.println(car_id + "  " + customer_id + "  " +  year + "  " + make + "  " + model
                                                + "  " + color + "  " + monthly + "  "  + balance);

                                        customerCar = Optional.of(
                                                new CustomerCar(car_id, customer_id, year, make, model, color, monthly, balance));
                                }
                        } catch (SQLException ex) {
                                ex.printStackTrace();
                        }
                        return customerCar;
                });
        }



    public void update(CustomerCar car) {
            String message = "The car to be updated should not be null";
            CustomerCar nonNullCustomerCar = Objects.requireNonNull(car, message);
            String sql = "UPDATE project0.customer_cars "
            + "SET "
            + "customer_id = ?, "
            + "year = ?, "
            + "make = ?, "
            + "model = ?, "
            + "color = ?, "
            + "monthly_payments = ?, "
            + "balance = ? "
            + "WHERE "
            + "car_id = ?";

            connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1,nonNullCustomerCar.getCustomer_id());
                statement.setString(2, nonNullCustomerCar.getYear());
                statement.setString(3, nonNullCustomerCar.getMake());
                statement.setString(4, nonNullCustomerCar.getModel());
                statement.setString(5, nonNullCustomerCar.getColor());
                statement.setDouble(6, nonNullCustomerCar.getMonthlyPayment());
                statement.setDouble(7, nonNullCustomerCar.getBalance());
                statement.setInt(8, nonNullCustomerCar.getCar_id());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
            ex.printStackTrace();
            }
            });
            }

    public void delete(CustomerCar car) {
            String message = "The car to be deleted should not be null";
            CustomerCar nonNullCustomerCar = Objects.requireNonNull(car, message);
            String sql = "DELETE FROM project0.customer_cars WHERE car_id = ?";

            connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullCustomerCar.getCar_id());

                int numberOfDeletedRows = statement.executeUpdate();

            } catch (SQLException ex) {
            ex.printStackTrace();
            }
            });
    }

}
