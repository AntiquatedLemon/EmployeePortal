package com.revature.controllers;

import com.revature.daos.RoleDAO;
import io.javalin.http.Handler;

public class RoleController {

    //RoleDAO to use methods
    RoleDAO rDAO = new RoleDAO();

    //handler will get patch req to update a Role title
    public Handler updateTitleHandler = (ctx) -> {
        //if not work then go back to the role_id or user_id
        String username = ctx.pathParam("username");

        //string to hold new role title to be put in; no parse because body already returns string
       String new_role_title = ctx.body(); //just put in the new title

        //postman
        if(rDAO.updateRoleTitle(username, new_role_title)){
            ctx.status(202); //accepted
        }else{
            ctx.status(406); //not acceptable
        }

    };


    //console menu thing is an old feature and it should have been removed
    //register assumes manager knows that he is a manager
}
