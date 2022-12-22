package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.models.AuthDTO;
import com.revature.models.Users;
import io.javalin.http.Handler;

public class ReimbursementController {
    public Handler Handler = (ctx) -> {
        String body = ctx.body(); //retrieve http request data
        Gson gson = new Gson(); //json to java conversion
        AuthDTO authDTO = gson.fromJson(body, AuthDTO.class);

        //if successful then populate session details
        Users loggedInUser = aDAO.login(authDTO.getUsername(), authDTO.getPword());

        if(loggedInUser != null){

            sesh = ctx.req.getSession();
            sesh.setAttribute("user_roles_id", loggedInUser.getRole().getRole_id());
            sesh.setAttribute("user_id", loggedInUser.getUser_id());

            //convert user back, going to HTTP Response
            String userJSON = gson.toJson(loggedInUser);

            ctx.result(userJSON);
            ctx.status(202); //accepted
        }else{
            ctx.status(401); //unauthorized
        }

    };
}
