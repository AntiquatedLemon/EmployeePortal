package com.revature;

import com.revature.utils.ConnectionUtil;
import io.javalin.Javalin;

import java.sql.Connection;
import java.sql.SQLException;

public class Launcher {
    public static void main(String[] args) {
        //database connection test, using try with resources
        try (Connection conn = ConnectionUtil.getConnection()) {
            System.out.println("CONNECTION SUCCESSFUL :)");
        } catch (SQLException e) {
            System.out.println("connection failed :(");
            e.printStackTrace();
        }
    }

    //javalin object
    Javalin app = Javalin.create(
            config -> {
                config.enableCorsForAllOrigins();
                //temp function to process http requests from anywhere
            }
    ).start(3000);
}
