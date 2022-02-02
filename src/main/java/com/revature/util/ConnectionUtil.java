package com.revature.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.DriverManager;

public class ConnectionUtil {

    public static Connection getConnection() throws SQLException{

        //manually register driver in case of different versions
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        String url = "jdbc:postgresql://34.67.25.246:5432/postgres";
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PW");
        return DriverManager.getConnection(url, username, password);

    }

}
