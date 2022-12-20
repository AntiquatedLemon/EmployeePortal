package com.revature;

import com.revature.controllers.AuthController;
import com.revature.controllers.RoleController;
import com.revature.controllers.UserController;
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


        //javalin object
        Javalin app = Javalin.create(
                config -> {
                    config.enableCorsForAllOrigins();
                    //temp function to process http requests from anywhere
                }
        ).start(3000);

        UserController uc = new UserController();
        RoleController rc = new RoleController();
        AuthController ac = new AuthController();

        //take GET requests for getusers
        app.get("/users", uc.getEmployeesHandler);

        //post requests for add to database
        app.post("/users", uc.insertUser);

        //update role title
        app.patch("/roles/{title}", rc.updateTitleHandler);

        //endpoint handler for login
        app.post("/login", ac.loginHandler);
    }
}