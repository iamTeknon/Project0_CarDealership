package com.automart.jdbc.dao;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.Offers;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class ImplementOffersDao implements Dao<Offers, Integer> {
    private final Optional<Connection> connection;

    public ImplementOffersDao() {
        this.connection = AwsConnection.getConnection();
    }

    @Override
    public Optional<Integer> save(Offers offer) {
        String message = "The offer to be added should not be null";
        Offers nonNullOffers = Objects.requireNonNull(offer, message);

        String sql = "INSERT INTO "
                + "project0.offers(customer_id, automart_car_id, offer, verdict) "
                + "VALUES(?, ?, ?, ?)";

        return connection.flatMap(conn -> {
            Optional<Integer> generatedId = Optional.empty();

            try (PreparedStatement statement =
                         conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setInt(1, nonNullOffers.getCustomerId());
                statement.setInt(2, nonNullOffers.getVehicleId());
                statement.setDouble(3, nonNullOffers.getOffer());
                statement.setString(4, nonNullOffers.getVerdict());


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

    public Optional<Offers> get(int id) {
        return connection.flatMap(conn -> {
            Optional<Offers> offer = Optional.empty();
            String sql = "SELECT offer_id, customer_id, automart_car_id, offer, verdict" +
                    " FROM project0.offers WHERE offer_id = " + id;
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                if (resultSet.next()) {
                    int offer_id = resultSet.getInt("offer_id");
                    int customer_id = resultSet.getInt("customer_id");
                    int automart_car_id = resultSet.getInt("automart_car_id");
                    double myOffer = resultSet.getDouble("offer");
                    String verdict = resultSet.getString("verdict");

                    System.out.println(offer_id + "  " + customer_id + "  " +  automart_car_id + "  "
                            + offer + "  " + verdict);

                    offer = Optional.of(
                            new Offers(offer_id, customer_id, automart_car_id, myOffer, verdict));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return offer;
        });
    }

    public Optional<Offers> getEverything() {
        return connection.flatMap(conn -> {
            Optional<Offers> offer = Optional.empty();
            String sql = "SELECT * FROM project0.offers";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int offer_id = resultSet.getInt("offer_id");
                    int customer_id = resultSet.getInt("customer_id");
                    int automart_car_id = resultSet.getInt("automart_car_id");
                    double myOffer = resultSet.getDouble("offer");
                    String verdict = resultSet.getString("verdict");

                    System.out.println(offer_id + "  " + customer_id + "  " +  automart_car_id + "  "
                            + offer + "  " + verdict);

                    offer = Optional.of(
                            new Offers(offer_id, customer_id, automart_car_id, myOffer, verdict));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return offer;
        });
    }



    public void update(Offers offer) {
        String message = "The offer to be updated should not be null";
        Offers nonNullOffers = Objects.requireNonNull(offer, message);
        String sql = "UPDATE project0.offers "
                + "SET "
                + "customer_id = ?, "
                + "automart_car_id = ?, "
                + "offer = ?, "
                + "verdict = ?"
                + "WHERE "
                + "offer_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {
                statement.setInt(1,nonNullOffers.getCustomerId());
                statement.setInt(2, nonNullOffers.getVehicleId());
                statement.setDouble(3, nonNullOffers.getOffer());
                statement.setInt(4, nonNullOffers.getOfferId());
                statement.setString(5, nonNullOffers.getVerdict());

                int numberOfUpdatedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

    public void delete(Offers offer) {
        String message = "The offer to be deleted should not be null";
        Offers nonNullOffers = Objects.requireNonNull(offer, message);
        String sql = "DELETE FROM project0.offers WHERE offer_id = ?";

        connection.ifPresent(conn -> {
            try (PreparedStatement statement = conn.prepareStatement(sql)) {

                statement.setInt(1, nonNullOffers.getOfferId());

                int numberOfDeletedRows = statement.executeUpdate();

            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        });
    }

}
