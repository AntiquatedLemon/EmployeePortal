package com.revature.daos;

import com.revature.models.ReimbursementType;
import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReTypeDAO {

    public ReimbursementType getTypeByID(int reimb_id) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //sql string
            String sql = "select * from reimbursement_type where reimb_id = ?";
            //Prepared statement so that we can fill appropriate values
            PreparedStatement ps = conn.prepareStatement(sql);
            //using ps.setXZY we can fill the wildcards (?) in our SQL statement
            ps.setInt(1, reimb_id);
            //execute the update!
            ResultSet rs = ps.executeQuery();
            // if there are results in the resultset, fill a role all-args constructor - make new role
            if (rs.next()){
                //changed to if statement since the while loop was being SUPREMELY pissy with me
                ReimbursementType reType = new ReimbursementType(
                        rs.getInt("reimb_id"),
                        rs.getString("reimb_type")
                );
                return reType;
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateReType(int reimb_id, String reType) {
        try(Connection conn = ConnectionUtil.getConnection()){

            //sql string
            String sql = "update reimbursement_type set reimb_type = ? where reimb_id = ?";
            //Prepared statement so that we can fill appropriate values
            PreparedStatement ps = conn.prepareStatement(sql);

            //using ps.setXZY we can fill the wildcards (?) in our SQL statement
            ps.setInt(1, reimb_id);
            ps.setString(2, reType);

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
