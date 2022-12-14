package com.revature.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//manage and establish database connection
public class ConnectionUtil {
    //make object of type Connection, connect to database
    public static Connection getConnection() throws SQLException {

        //For compatibility with other technologies/frameworks, we'll need to register our PostgresSQL driver
        //This process makes the application aware of what Driver class we're using
        try {
            Class.forName("org.postgresql.Driver"); //searching for the postgres driver, which we have as a dependency
        } catch (ClassNotFoundException e) {
            e.printStackTrace(); //This tells in the console us what went wrong
            System.out.println("problem occurred locating driver");
        }
        //database credentials to establish a database connection
        //***perhaps want to be a little show-off later and hide them in environment variables
        //credentials in Strings, use the strings to get connections

        String url = "jdbc:postgresql://localhost:5432/postgres?currentSchema=user_roles";
        String username = "postgres";
        String password = "password"; //actual password lol

        //return statement gives the actual database Connection object
        return DriverManager.getConnection(url, username, password);



    }
}
