package com.revature.models;

public class ReimbursementStatus {
    private int reimb_status_id;
    private String reimb_status;

    //boilerplate stuff

    //no args
    public ReimbursementStatus(){
    }
    //all args
    public ReimbursementStatus(int reimb_status_id, String reimb_status) {
        this.reimb_status_id = reimb_status_id;
        this.reimb_status = reimb_status;
    }

    //all args minus id, serial in the database will handle that
    public ReimbursementStatus(String reimb_status) {
        this.reimb_status = reimb_status;
    }

    //getters and setters


    public int getReimb_status_id() {
        return reimb_status_id;
    }

    public void setReimb_status_id(int reimb_status_id) {
        this.reimb_status_id = reimb_status_id;
    }

    public String getReimb_status() {
        return reimb_status;
    }

    public void setReimb_status(String reimb_status) {
        this.reimb_status = reimb_status;
    }

    @Override
    public String toString() {
        return "ReimbursementStatus{" +
                "reimb_status_id=" + reimb_status_id +
                ", reimb_status='" + reimb_status + '\'' +
                '}';
    }
}


