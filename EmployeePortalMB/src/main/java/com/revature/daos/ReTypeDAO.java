package com.revature.daos;

import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReTypeDAO implements ReTypeDAOInterface {

    @Override
    public ReTypeDAO getTypeByID(int reimb_id, String type) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //sql string
            String sql = "select * from reimbursement_type where reimb_id = ?";
            //Prepared statement so that we can fill appropriate values
            PreparedStatement ps = conn.prepareStatement(sql);

            //using ps.setXZY we can fill the wildcards (?) in our SQL statement
            ps.setInt(1, reimb_id);
            ps.setString(2, type);

            //execute the update!
            ps.executeUpdate();

            //if we get this far in the try block, we can assume nothing went wrong. return true.
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean updateReType(int reimb_id, String type) {
        try(Connection conn = ConnectionUtil.getConnection()){

            //sql string
            String sql = "update reimbursement_type set reimb_type = ? where reimb_id = ?";
            //Prepared statement so that we can fill appropriate values
            PreparedStatement ps = conn.prepareStatement(sql);

            //using ps.setXZY we can fill the wildcards (?) in our SQL statement
            ps.setInt(1, reimb_id);
            ps.setString(2, type);

            //execute the update!
            ps.executeUpdate();

            //if we get this far in the try block, we can assume nothing went wrong. return true.
            return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
