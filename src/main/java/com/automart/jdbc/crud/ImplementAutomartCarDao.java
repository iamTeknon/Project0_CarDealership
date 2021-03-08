package com.automart.jdbc.crud;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.AutomartCar;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class ImplementAutomartCarDao implements Dao<AutomartCar, Integer>{
    private final Optional<Connection> connection;

    public ImplementAutomartCarDao() {
        this.connection = AwsConnection.getConnection();
    }

    @Override
    public Optional<Integer> save(AutomartCar automartCar) {
        String message = "The car to be added should not be null";
        AutomartCar nonNullAutomartCar = Objects.requireNonNull(automartCar, message);
        String sql = "INSERT INTO "
                + "project0.automart_cars(year, make, model, color, price) "
                + "VALUES(?, ?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(
                                 sql,
                                 Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, nonNullAutomartCar.getYear());
                statement.setString(2, nonNullAutomartCar.getMake());
                statement.setString(3, nonNullAutomartCar.getModel());
                statement.setString(4, nonNullAutomartCar.getColor());
                statement.setBigDecimal(4, nonNullAutomartCar.getPrice());

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

    public Optional<AutomartCar> get(int id) {
        return connection.flatMap(conn -> {
            Optional<AutomartCar> automartCar = Optional.empty();
            String sql = "SELECT automart_car_id, year, make, model, color, price" +
                    " FROM project0.automart_cars WHERE automart_car_id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int automart_car_id = resultSet.getInt("automart_car_id");
                    int year = resultSet.getInt("year");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    String color = resultSet.getString("color");
                    BigDecimal price = resultSet.getBigDecimal("price");

                    System.out.println(automart_car_id + "  " +  year + ", " + make + "  " + model
                            + "  " + color + "  " + price);

                    automartCar = Optional.of(
                            new AutomartCar(automart_car_id, year, make, model, color, price));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return automartCar;
        });
    }

    public Collection<AutomartCar> getAll() {
        Collection<AutomartCar> automartCars = new ArrayList<>();
        String sql = "SELECT * FROM project0.automart_cars";

        connection.ifPresent(conn -> {
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int id = resultSet.getInt("automart_car_id");
                    int year = resultSet.getInt("year");
                    String make = resultSet.getString("make");
                    String model = resultSet.getString("model");
                    String color = resultSet.getString("color");
                    BigDecimal price = resultSet.getBigDecimal("price");

                    System.out.println(id + "  " + year + "  " + make + "  " + model
                            + "  " +  color + "  " + price);

                    AutomartCar automartCar = new AutomartCar(id, year, make, model, color, price);

                    automartCars.add(automartCar);
                }

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
        return automartCars;
    }

    public void update(AutomartCar automartCar) {
        String message = "The car to be updated should not be null";
        AutomartCar nonNullAutomartCar = Objects.requireNonNull(automartCar, message);
        String sql = "UPDATE project0.automart_cars "
                + "SET "
                + "year = ?, "
                + "make = ?, "
                + "model = ?, "
                + "color = ?, "
                + "price = ? "
                + "WHERE "
                + "automart_car_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1, nonNullAutomartCar.getYear());
                statement.setString(2, nonNullAutomartCar.getMake());
                statement.setString(3, nonNullAutomartCar.getModel());
                statement.setString(4, nonNullAutomartCar.getColor());
                statement.setBigDecimal(5, nonNullAutomartCar.getPrice());
                statement.setInt(6, nonNullAutomartCar.getId());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void delete(AutomartCar automartCar) {
        String message = "The car to be deleted should not be null";
        AutomartCar nonNullAutomartCar = Objects.requireNonNull(automartCar, message);
        String sql = "DELETE FROM project0.automart_cars WHERE automart_car_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullAutomartCar.getId());

                int numberOfDeletedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }
}

