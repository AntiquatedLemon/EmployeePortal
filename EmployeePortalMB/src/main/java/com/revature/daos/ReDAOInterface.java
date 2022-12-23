package com.revature.daos;

import com.revature.models.Reimbursement;

import java.util.ArrayList;

public interface ReDAOInterface {
    ArrayList<Reimbursement> getReimbursement();

    //new ticket
    Reimbursement newTicket (Reimbursement ticket);
    //    boolean newTicket (double amt, String desc, String type); finish out later

    //view specific ticket
    Reimbursement getReimbByRID (int reimb_id);
    ArrayList<Reimbursement> getAllReimbByUID (int user_id);
    //update ticket
    boolean updateStatus(int reimb_id, int reStatus);
}
