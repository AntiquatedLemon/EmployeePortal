package com.revature.daos;

import com.revature.models.ReimbursementStatus;
import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ReStatusDAO {
    /*
    Manger ArrayList = all pending, all pending by user
    Employee View = all pending for user
    select amt/type/desc/status
    from reimbursement and reimbursement_status
    where reimb_status = 'pending'

    ^ I think that resolves the "should be removed from
    the list/queue once processed because it's filtered down
    */

    //filter the status
    //sql statements look fine
    public ReimbursementStatus getStatusbyID(int reimb_id) {
        try(Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select reimb_status from reimbursement_status where reimb_id = ?;";
           //fix the sql statement--------------------------------------------------------------------------------------------------------------------------------------------
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, reimb_id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ReimbursementStatus reStatus = new ReimbursementStatus(
                        rs.getInt("reimb_id"),
                        rs.getString("reimb_status")
                );
                return reStatus;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

        //this would update my default table (sql) so no, sillly noodle!
//    public boolean updateStatus(int reimb_id, String reStatus) {
//        try(Connection conn = ConnectionUtil.getConnection()){
//            String sql = "update reimbursement set reimb_status = ? where reimb_id = ?";
//            PreparedStatement ps = conn.prepareStatement(sql);
//            ps.setInt(1, reimb_id);
//            ps.setString(2, reStatus);
//            ps.executeUpdate();
//            return true;
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//        return false;
//    }

}



