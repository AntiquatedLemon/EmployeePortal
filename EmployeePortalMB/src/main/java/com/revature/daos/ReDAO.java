package com.revature.daos;

import com.revature.models.*;
import com.revature.utils.ConnectionUtil;

import javax.jws.soap.SOAPBinding;
import java.sql.*;
import java.util.ArrayList;

public class ReDAO implements ReDAOInterface{
    @Override
    public ArrayList<Reimbursement> getReimbursement() {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from reimbursement;"; //get all reimbursements

            //statement because no wildcards
            Statement s = conn.createStatement();

            //execute query to save them into the resultset
            ResultSet rs = s.executeQuery(sql);

            ArrayList <Reimbursement> ReimbursementList = new ArrayList();

            while (rs.next()) {
                Reimbursement re = new Reimbursement(
                        rs.getInt("reimb_id"),
                        rs.getDouble("reimb_amt"),
                        rs.getString("reimb_desc"),
                        null,
                        null,
                        null,
                        null,
                        rs.getInt("reimb_id_fk_users"), //get user value through fk
                        rs.getInt("reimb_id_fk_user2"),
                        rs.getInt("reimb_id_fk_type"),//connect to reimb type
                        rs.getInt("reimb_id_ik_status") //connect to reimb_status
                        ); //null as no jdbc obj for user (yet)
                int userFK =  rs.getInt("reimb_id_fk_users"); //get user value through fk
                int usernameFK = rs.getInt("reimb_id_fk_user2");
                int typeFK = rs.getInt("reimb_id_fk_type");//connect to reimb type
                int statusFK = rs.getInt("reimb_id_ik_status"); //connect to reimb_status

                //user obj to get user data
                UsersDAO usersDAO = new UsersDAO();
                Users u = usersDAO.getUserByID(userFK);
                re.setUser(u);
                //type obj to get type data
                ReTypeDAO reTypeDAO = new ReTypeDAO();
                ReimbursementType rt = reTypeDAO.getTypeByID(typeFK);
                re.setRe_type(rt);

                //status obj to get status data
                ReStatusDAO reStatusDAO = new ReStatusDAO();
                ReimbursementStatus rst = reStatusDAO.getStatusbyID(statusFK);
                re.setRe_status(rst);

                //add to arraylist
                ReimbursementList.add(re);
            } //close while loop
            return ReimbursementList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public Reimbursement newTicket(Reimbursement insTicket) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into reimbursement (reimb_amt, reimb_desc, reimb_id_fk_users, reimb_id_fk_users2, reimb_id_fk_type, reimb_id_fk_status) values (?, ?, ?, ?, ?, ? )";
            //instantiate preparedstatement for dql and filling in var
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill out the wildcards using the user object in the args
            ps.setString(1, "reimb_amt");
            ps.setString(2, "reimb_desc");
            ps.setInt(3, "reimb_id_fk_users");
            ps.setString(4, "reimb_id_fk_users2");
            ps.setInt(5, " reimb_id_fk_type");
            ps.setInt(6, " reimb_id_fk_status");

            //execute update
            ps.executeQuery();

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Reimbursement viewTicket(Reimbursement viewTicket) {
        return null;
    }

    @Override
    public Reimbursement updateTicket(Reimbursement updTicket) {
        return null;
    }



}

