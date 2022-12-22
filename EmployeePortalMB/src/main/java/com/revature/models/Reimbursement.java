package com.revature.models;

public class Reimbursement {
/*
Must have an amount
Must have a description
Should have a default status of Pending
 */

    //variables needed, id, amt and desc
    private int reimb_id;
    private double reimb_amt;
    private String reimb_desc;

    //user variables
    private Users user;
    private Users username;

    //reimbursement variables
    private ReimbursementType re_type;
    private ReimbursementStatus re_status;

    //handle my keys
    private int reimb_id_fk_users; //users(user_id)
    private int reimb_id_fk_username; //users(usernames)
    private int reimb_id_fk_type; //reimbursement_type(reimb_type_id)
    private int reimb_id_fk_status; //reimbursement_status(reimb_status_id)

    //no args
    public Reimbursement() {
    }

    //all args
    public Reimbursement(int reimb_id, double reimb_amt, String reimb_desc, Users user, Users username, ReimbursementType re_type, ReimbursementStatus re_status, int reimb_id_fk_users, int reimb_id_fk_username, int reimb_id_fk_type, int reimb_id_fk_status) {
        this.reimb_id = reimb_id;
        this.reimb_amt = reimb_amt;
        this.reimb_desc = reimb_desc;
        this.user = user;
        this.username = username;
        this.re_type = re_type;
        this.re_status = re_status;
        this.reimb_id_fk_users = reimb_id_fk_users;
        this.reimb_id_fk_username = reimb_id_fk_username;
        this.reimb_id_fk_type = reimb_id_fk_type;
        this.reimb_id_fk_status = reimb_id_fk_status;
    }

    //all args - id, and fks, help with post requests
    public Reimbursement(double reimb_amt, String reimb_desc, Users user, Users username, ReimbursementType re_type, ReimbursementStatus re_status, int reimb_id_fk_users, int reimb_id_fk_username, int reimb_id_fk_type, int reimb_id_fk_status) {
        this.reimb_amt = reimb_amt;
        this.reimb_desc = reimb_desc;
        this.user = user;
        this.username = username;
        this.re_type = re_type;
        this.re_status = re_status;
        this.reimb_id_fk_users = reimb_id_fk_users;
        this.reimb_id_fk_username = reimb_id_fk_username;
        this.reimb_id_fk_type = reimb_id_fk_type;
        this.reimb_id_fk_status = reimb_id_fk_status;
    }

    public int getReimb_id() {
        return reimb_id;
    }

    public void setReimb_id(int reimb_id) {
        this.reimb_id = reimb_id;
    }

    public double getReimb_amt() {
        return reimb_amt;
    }

    public void setReimb_amt(double reimb_amt) {
        this.reimb_amt = reimb_amt;
    }

    public String getReimb_desc() {
        return reimb_desc;
    }

    public void setReimb_desc(String reimb_desc) {
        this.reimb_desc = reimb_desc;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Users getUsername() {
        return username;
    }

    public void setUsername(Users username) {
        this.username = username;
    }

    public ReimbursementType getRe_type() {
        return re_type;
    }

    public void setRe_type(ReimbursementType re_type) {
        this.re_type = re_type;
    }

    public ReimbursementStatus getRe_status() {
        return re_status;
    }

    public void setRe_status(ReimbursementStatus re_status) {
        this.re_status = re_status;
    }

    public int getReimb_id_fk_users() {
        return reimb_id_fk_users;
    }

    public void setReimb_id_fk_users(int reimb_id_fk_users) {
        this.reimb_id_fk_users = reimb_id_fk_users;
    }

    public int getReimb_id_fk_username() {
        return reimb_id_fk_username;
    }

    public void setReimb_id_fk_username(int reimb_id_fk_username) {
        this.reimb_id_fk_username = reimb_id_fk_username;
    }

    public int getReimb_id_fk_type() {
        return reimb_id_fk_type;
    }

    public void setReimb_id_fk_type(int reimb_id_fk_type) {
        this.reimb_id_fk_type = reimb_id_fk_type;
    }

    public int getReimb_id_fk_status() {
        return reimb_id_fk_status;
    }

    public void setReimb_id_fk_status(int reimb_id_fk_status) {
        this.reimb_id_fk_status = reimb_id_fk_status;
    }

    @Override
    public String toString() {
        return "Reimbursement{" +
                "reimb_id=" + reimb_id +
                ", reimb_amt=" + reimb_amt +
                ", reimb_desc='" + reimb_desc + '\'' +
                ", user=" + user +
                ", username=" + username +
                ", re_type=" + re_type +
                ", re_status=" + re_status +
                ", reimb_id_fk_users=" + reimb_id_fk_users +
                ", reimb_id_fk_username=" + reimb_id_fk_username +
                ", reimb_id_fk_type=" + reimb_id_fk_type +
                ", reimb_id_fk_status=" + reimb_id_fk_status +
                '}';
    }
}
