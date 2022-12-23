package com.revature.controllers;

import com.revature.daos.ReDAO;
import com.revature.daos.ReStatusDAO;
import io.javalin.http.Handler;

import javax.servlet.http.HttpSession;

public class ReimbursementStatusController {
    ReDAO rDAO = new ReDAO();
    ReStatusDAO rsDAO = new ReStatusDAO();
    public static HttpSession sesh;
    //check if login has happened

    public Handler updateStatusHandler = (ctx) -> {
        if(AuthController.sesh != null){
            if((Integer)AuthController.sesh.getAttribute("user_roles_id") == 1) {
                int reimb_id = Integer.parseInt(ctx.pathParam("reimb_id")); //specific ticket to be changed
                int reimb_id_status = Integer.parseInt(ctx.body()); //retrieve http request data, the new status id (between java and postman)
                int reimb_status_id = rDAO.getReimbByRID(reimb_id).getReimb_id_fk_status(); //current status of ticket

                if (reimb_status_id != 1) {
                    ctx.status(406);
                    ctx.result("This ticket has reached final status.");
                } else {
                    if (rDAO.updateStatus(reimb_id, reimb_id_status)) {
                        ctx.status(202); //accepted change
                        ctx.result("Your change has been accepted");
                    } else {
                        ctx.status(406);
                        ctx.result("this is in UNACCEPTABLE CONDITION!!! -Lemongrab, Adventure Time 'Too Young;");
                    }
                }
            }else{
                ctx.status(401);
                ctx.result("You literally cant do that, you're not a manager.");
            }
        }else{
            ctx.status(401);
            ctx.result("You aren't even logged in.");
        }

    };



}
