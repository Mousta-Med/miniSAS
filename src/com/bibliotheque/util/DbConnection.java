package com.bibliotheque.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {

    static Connection connection;
    public static  Connection createDbConnection(){
        try {
            String url = "jdbc:postgresql://localhost:5432/bibliotheque";
            String user = "postgres";
            String password = "mousta";
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}



