package com.revature.controllers;

import com.google.gson.Gson;
import com.revature.daos.ReDAO;
import com.revature.models.ReDTO;
import com.revature.models.Reimbursement;
import com.revature.models.Users;
import io.javalin.http.Handler;

import java.util.ArrayList;


public class ReimbursementController {

    ReDAO rDAO = new ReDAO();

    //fill upon login HttpSession obj
    //check if login has happened
    public Handler newTicketHandler = (ctx) -> {

        if (AuthController.sesh != null) {

            String body = ctx.body();
            Gson gson = new Gson(); //json to java conversion
            ReDTO rDTO = gson.fromJson(body, ReDTO.class);

            if (rDTO != null) {
                //but user id of the person logged in to go into reimbursement obj (to later pass to rdao for new ticket)
                Reimbursement r = new Reimbursement(rDTO.getReimb_amt(), rDTO.getReimb_desc(), (Integer) AuthController.sesh.getAttribute("user_id"));
                //turn into user obj
                //Reimbursement newTicket = gson.fromJson(body, Reimbursement.class);
                //^ useless now that Reimb obj made because need a way to pass it through
                if (rDAO.newTicket(r) != null) { //given that insert is successful - that is returned a ticket
                    ctx.status(201); //created
                    ctx.result(body); //returns the reimb entry from the user
                } else {
                    ctx.status(406); //not acceptable
                    ctx.result("Ticket creation failed.");
                }
            } else {
                ctx.status(406);
                ctx.result("SIs, do you want your money back or not? Please input an amount and a description of the expense.");
            }
        } else{
            ctx.status(401);
            ctx.result("You go here, right? Why aren't you logged in?");
        }
    };

    public Handler pendingTicketHandler = (ctx) ->{
        //dude, are you even signed in??
       if(AuthController.sesh != null){
           //are you a manager or nah?
            if((Integer)AuthController.sesh.getAttribute("user_roles_id") == 1){
                String body = ctx.body();
                ArrayList<Reimbursement> List = rDAO.getReimbByStatus(Integer.parseInt(body));
                Gson gson = new Gson();
                String ts = gson.toJson(List);
                ctx.status(202);
                ctx.result(ts);
            }
            else{
                ctx.status(401);
                ctx.result("Authorized? Nah, go get a promotion and try again");

            }
       }else{
           ctx.status(401);
           ctx.result("You go here, right? Why aren't you logged in?");
       }
    };

    public Handler userViewTicketHandler = (ctx) -> {
        if(AuthController.sesh != null){
            if((Integer)AuthController.sesh.getAttribute("user_roles_id") == 1){
                String body = ctx.body();
                ArrayList <Reimbursement> List = rDAO.getAllReimbByUID(Integer.parseInt(body));
                Gson gson = new Gson();
                String ts = gson.toJson(List);
                ctx.status(202);
                ctx.result(ts);
            }
            else{
                ctx.status(401);
                ctx.result("Authorized? Nah, go get a promotion and try again");

            }
        }else{
            ctx.status(401);
            ctx.result("You go here, right? Why aren't you logged in?");
        }
    };


}
