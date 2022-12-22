package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.UsersDAO;
import com.revature.models.Users;
import io.javalin.http.Handler;

import java.util.ArrayList;

public class UserController {

    //need user dao obj for methods
    UsersDAO uDAO = new UsersDAO();

    //handles http get requests for get all users
    //then send that users data back in a http response
    public Handler getEmployeesHandler = (ctx) -> {
        //if not null, they're logged in
        // AS A MANAGER????
        if(AuthController.sesh != null){
            //retrieve saved sessions
            ArrayList<Users> users = uDAO.getUsers();
            Gson gson = new Gson();
            String JSONUsers = gson.toJson(users);

            //send back http response; request all employee data
            ctx.result(JSONUsers);
            ctx.status(202); //accepted
        } else {
            ctx.result("Log in is required to perform that activity.");
            ctx.status(401); //unauthorized
        }
    };

    public Handler insertUser = (ctx) -> {
        //get that JSON data through ctx.body
        String body = ctx.body();
        Gson gson = new Gson(); //json to java conversion
        //turn into user obj
        Users newUser = gson.fromJson(body, Users.class);

        if(uDAO.insertUsers(newUser.getUsername(), newUser.getPword())){ //given that insert is successful - that is returned a user
            ctx.status(201); //created
            ctx.result(body); //returns the user entry
        } else {
            ctx.status(406); //not acceptable
            ctx.result("Username is taken. Please don't try again.");
        }

    };
}
