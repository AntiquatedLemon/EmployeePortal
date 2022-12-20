package com.revature.daos;

import com.revature.models.Role;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RoleDAO implements RoleDAOInterface{
    //inherit all por favor
    //sql statement, with variable

    @Override
    public Role getRoleByID(int ID) {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from user_roles where user_roles_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()){
                //changed to if statement since the while loop was being SUPREMELY pissy with me
                Role role = new Role(
                        rs.getInt("user_roles_id"),
                        rs.getString("user_roles_title")
                );
                return role;
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //could make this include salary
    @Override
    public boolean updateRoleTitle(String title) {
        try(Connection conn = ConnectionUtil.getConnection()){
            //where user role int = ?, update the title of the user to ? in table user_roles
            String sql = "update user_roles set user_role_title = ? where user_roles_id = ?;";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, title);
            ps.executeUpdate();
            return true;

        }catch(SQLException e){
            e.printStackTrace();
        }
        return false;
    }
}
