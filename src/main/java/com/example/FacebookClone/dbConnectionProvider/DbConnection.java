package com.example.FacebookClone.dbConnectionProvider;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
    private static Connection connection;

    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/facebook_clone?autoReconnect=true&serverTimezone=UTC&useSSL=False&allowPublicKeyRetrieval=true",
                    "root", "mysqlroot");
        }catch (Exception e){
            e.printStackTrace();
        }
        return connection;
    }
}
