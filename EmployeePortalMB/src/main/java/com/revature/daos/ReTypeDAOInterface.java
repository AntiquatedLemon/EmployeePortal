package com.revature.daos;

public interface ReTypeDAOInterface {
    //method that returns a reimbursement type when given a reimbursement ID
    //referencing user id
    ReTypeDAO getTypeByID(int reimb_id, String type);

    //method to update Role type
    //
    boolean updateReType(int reimb_id, String type);
}
