package com.automart.jdbc.connect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Optional;

// The following code was borrowed from Hiram Kamau and modified for this project
// https://stackabuse.com/working-with-postgresql-in-java/
public class AwsConnection {
    private static Optional<Connection> connection = Optional.empty();

    public static Optional<Connection> getConnection(){
        if(!connection.isPresent()){
            String url = "jdbc:postgresql://enterprise2102.cxovyplivamc.us-east-2.rds.amazonaws.com:5432/aws";
            String user = "postgres";
            String password = "postgres";

            try{
                connection = Optional.ofNullable(DriverManager.getConnection(url, user, password));
            }catch (SQLException ex){
                ex.printStackTrace();
            }
        }
        return connection;
    }
}
