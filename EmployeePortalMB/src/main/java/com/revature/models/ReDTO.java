package com.revature.models;
//store data from postman
public class ReDTO {
    private double reimb_amt;
    private String reimb_desc;

    public ReDTO(double reimb_amt, String reimb_desc) {
        this.reimb_amt = reimb_amt;
        this.reimb_desc = reimb_desc;
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
}
