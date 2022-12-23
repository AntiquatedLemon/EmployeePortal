package com.revature.daos;

import com.revature.models.*;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class ReDAO implements ReDAOInterface{

    //get all reimbursements
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
                        rs.getString("reimb_id_fk_user2"),
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

    //create a new ticket
//    @Override
//    public boolean newTicket(double amt, String desc, String type) {
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "insert into reimbursement (reimb_amt, reimb_desc, reimb_id_fk_users, reimb_id_fk_users2, reimb_id_fk_type, reimb_id_fk_status) values (?, ?, ?, ?, ?, ? )";
//            //instantiate preparedstatement for dql and filling in var
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ResultSet rs = ps.executeQuery();
//            Reimbursement re = new Reimbursement(
//                    ps.setDouble(1, amt),
//                    ps.setString(2, desc),
//                    ps.setString(3, "reimb_id_fk_users"),
//            );
//                return true;
//        }catch(SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

    @Override
    public Reimbursement newTicket(Reimbursement ticket) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "insert into reimbursement (reimb_amt, reimb_desc, reimb_id_fk_users, reimb_id_fk_status) values (?, ?, ?, 1)";
            //instantiate preparedstatement for dql and filling in var
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setDouble(1,ticket.getReimb_amt());
            ps.setString(2, ticket.getReimb_desc());
            ps.setInt(3, ticket.getReimb_id_fk_users());
            ps.executeUpdate();
            return ticket;
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //view specific user tickets
    @Override
    public Reimbursement getReimbByRID (int reimb_id) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from reimbursement where reimb_id = ?;"; //get all reimbursements
           PreparedStatement ps = conn.prepareStatement(sql);
           ps.setInt(1, reimb_id);
           //execute query to save them into the resultset
           ResultSet rs = ps.executeQuery();

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
                        rs.getString("reimb_id_fk_users2"),
                        rs.getInt("reimb_id_fk_type"),//connect to reimb type
                        rs.getInt("reimb_id_fk_status") //connect to reimb_status
                );
                //null as no jdbc obj for user (yet)
                int userFK =  rs.getInt("reimb_id_fk_users"); //get user value through fk
                String usernameFK = rs.getString("reimb_id_fk_users2");
                int typeFK = rs.getInt("reimb_id_fk_type");//connect to reimb type
                int statusFK = rs.getInt("reimb_id_fk_status"); //connect to reimb_status

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
                return re;
            } //close while loop
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
@Override
    public ArrayList<Reimbursement> getAllReimbByUID (int user_id) {
        ArrayList <Reimbursement> ReimbursementsList = new ArrayList();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from reimbursement where reimb_id_fk_users = ?;"; //get all reimbursements
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, user_id);
            //execute query to save them into the resultset
            ResultSet rs = ps.executeQuery();

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
                        rs.getString("reimb_id_fk_users2"),
                        rs.getInt("reimb_id_fk_type"),//connect to reimb type
                        rs.getInt("reimb_id_fk_status") //connect to reimb_status
                );
                //null as no jdbc obj for user (yet)
                int userFK =  rs.getInt("reimb_id_fk_users"); //get user value through fk
                String usernameFK = rs.getString("reimb_id_fk_users2");
                int typeFK = rs.getInt("reimb_id_fk_type");//connect to reimb type
                int statusFK = rs.getInt("reimb_id_fk_status"); //connect to reimb_status

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

                ReimbursementsList.add(re);
            } //close while loop
            return ReimbursementsList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public ArrayList<Reimbursement> getReimbByStatus (int reimb_id_fk_status) {
        ArrayList <Reimbursement> StatusList = new ArrayList();
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from reimbursement where reimb_id_fk_status= ?;"; //get all reimbursements
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimb_id_fk_status);
            //execute query to save them into the resultset
            ResultSet rs = ps.executeQuery();

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
                        rs.getString("reimb_id_fk_users2"),
                        rs.getInt("reimb_id_fk_type"),//connect to reimb type
                        rs.getInt("reimb_id_fk_status") //connect to reimb_status
                );
                //null as no jdbc obj for user (yet)
                int userFK =  rs.getInt("reimb_id_fk_users"); //get user value through fk
                String usernameFK = rs.getString("reimb_id_fk_users2");
                int typeFK = rs.getInt("reimb_id_fk_type");//connect to reimb type
                int statusFK = rs.getInt("reimb_id_fk_status"); //connect to reimb_status

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
                StatusList.add(re);
            } //close while loop
            return StatusList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    //manager update ticket
    @Override
    public boolean updateStatus(int reimb_id, int reStatus) {
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "update reimbursement set reimb_id_fk_status = ? where reimb_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reStatus);
            ps.setInt(2, reimb_id);
            ps.executeUpdate();
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

}

