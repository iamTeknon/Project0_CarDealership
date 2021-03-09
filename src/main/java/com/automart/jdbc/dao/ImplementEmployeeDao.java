package com.automart.jdbc.dao;

import com.automart.jdbc.connect.AwsConnection;
import com.automart.jdbc.entities.Employee;

import java.sql.*;
import java.util.Objects;
import java.util.Optional;

public class ImplementEmployeeDao implements Dao<Employee, Integer> {
        private final Optional<Connection> connection;

    public ImplementEmployeeDao() {
            this.connection = AwsConnection.getConnection();
        }

        @Override
        public Optional<Integer> save(Employee employee) {
            String message = "The employee to be added should not be null";
            Employee nonNullEmployee = Objects.requireNonNull(employee, message);
            String sql = "INSERT INTO "
                    + "project0.employees(last_name, first_name, email, phone_number) "
                    + "VALUES(?, ?, ?, ?)";

            return connection.flatMap(conn -> {
                Optional<Integer> generatedId = Optional.empty();

                try (PreparedStatement statement =
                             conn.prepareStatement(
                                     sql,
                                     Statement.RETURN_GENERATED_KEYS)) {
                    statement.setString(1, nonNullEmployee.getLastName());
                    statement.setString(2, nonNullEmployee.getFirstName());
                    statement.setString(3, nonNullEmployee.getEmail());
                    statement.setString(4, nonNullEmployee.getPhone());

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
                System.out.println("The employee has been added to the database.");
                return generatedId;
            });
        }

        public Optional<Employee> get(int id) {
            return connection.flatMap(conn -> {
                Optional<Employee> employee = Optional.empty();
                String sql = "SELECT employee_id, last_name, first_name, email, phone_number" +
                        " FROM project0.employees WHERE employee_id = " + id;
                try (Statement statement = conn.createStatement();
                     ResultSet resultSet = statement.executeQuery(sql)) {

                    if (resultSet.next()) {
                        int employee_id = resultSet.getInt("employee_id");
                        String lastName = resultSet.getString("last_name");
                        String firstName = resultSet.getString("first_name");
                        String email = resultSet.getString("email");
                        String phone = resultSet.getString("phone_number");

                        System.out.println(employee_id + "  " +  lastName + ", " + firstName + "  " + email
                                + "  " + phone);

                        employee = Optional.of(
                                new Employee(employee_id, lastName, firstName, email, phone));
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                return employee;
            });
        }

    public Optional<Employee> getEverything() {
        return connection.flatMap(conn -> {
            Optional<Employee> employee = Optional.empty();
            String sql = "SELECT * FROM project0.employees";
            try (Statement statement = conn.createStatement();
                 ResultSet resultSet = statement.executeQuery(sql)) {

                while (resultSet.next()) {
                    int employee_id = resultSet.getInt("employee_id");
                    String lastName = resultSet.getString("last_name");
                    String firstName = resultSet.getString("first_name");
                    String email = resultSet.getString("email");
                    String phone = resultSet.getString("phone_number");

                    System.out.println(employee_id + "  " +  lastName + ", " + firstName + "  " + email
                            + "  " + phone);

                    employee = Optional.of(
                            new Employee(employee_id, lastName, firstName, email, phone));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            return employee;
        });
    }

        public void update(Employee employee) {
            String message = "The employee to be updated should not be null";
            Employee nonNullEmployee = Objects.requireNonNull(employee, message);
            String sql = "UPDATE project0.employees "
                    + "SET "
                    + "last_name = ?, "
                    + "first_name = ?, "
                    + "email = ?, "
                    + "phone_number = ? "
                    + "WHERE "
                    + "employee_id = ?";

            connection.ifPresent(conn -> {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {

                    statement.setString(1, nonNullEmployee.getLastName());
                    statement.setString(2, nonNullEmployee.getFirstName());
                    statement.setString(3, nonNullEmployee.getEmail());
                    statement.setString(4, nonNullEmployee.getPhone());
                    statement.setInt(5, nonNullEmployee.getId());

                    int numberOfUpdatedRows = statement.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            System.out.println("The employees information has been updated in the database.");
        }

        public void delete(Employee employee) {
            String message = "The customer to be deleted should not be null";
            Employee nonNullEmployee = Objects.requireNonNull(employee, message);
            String sql = "DELETE FROM project0.employees WHERE employee_id = ?";

            connection.ifPresent(conn -> {
                try (PreparedStatement statement = conn.prepareStatement(sql)) {

                    statement.setInt(1, nonNullEmployee.getId());

                    int numberOfDeletedRows = statement.executeUpdate();

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            });
            System.out.println("The employee has been removed from the database.");
        }
}
