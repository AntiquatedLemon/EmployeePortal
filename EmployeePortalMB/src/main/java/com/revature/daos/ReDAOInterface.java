package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.ArrayList;

public interface ReDAOInterface {
    ArrayList<Reimbursement> getReimbursement();

    //new ticket
    Reimbursement newTicket(Reimbursement insTicket);

    //view ticket
    Reimbursement viewTicket(Reimbursement viewTicket);


    //update ticket
    Reimbursement updateTicket(Reimbursement updTicket);
}
