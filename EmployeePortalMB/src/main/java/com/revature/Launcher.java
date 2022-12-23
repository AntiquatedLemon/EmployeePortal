package com.revature;

import com.revature.controllers.*;
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
        ReimbursementStatusController rsc = new ReimbursementStatusController();
        ReimbursementController rbc = new ReimbursementController();

        //GET requests for all users
        app.get("/users", uc.getEmployeesHandler);
        //post requests for add user to database
        app.post("/insUsers", uc.insertUser);
        //endpoint handler for login
        app.post("/login", ac.loginHandler);

        app.patch("/updateTicket/{reimb_id}", rsc.updateStatusHandler);

        //create new ticket
        app.post("/createTicket", rbc.newTicketHandler);

        //view
        app.get("/viewTickets", rbc.pendingTicketHandler);

        app.get("/viewVTickets", rbc.userViewTicketHandler);
        //update role title, stretch goal, IGNORE
        app.patch("/roles/{title}", rc.updateTitleHandler);
    }
}