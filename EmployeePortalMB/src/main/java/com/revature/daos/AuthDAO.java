package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AuthDAO {
    //register a user is in UserController instead of here!!!

    //maybe abstract this later but right now? no
    public Users login(String username, String pword){
        try(Connection conn = ConnectionUtil.getConnection()){
            String sql = "select * from users where username = ? and pword = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);

            ps.setString(1, username);
            ps.setString(2, pword);

            ResultSet rs = ps.executeQuery();

            //logging in only takes one record rn sooooo no while :)
            if (rs.next()){
                Users u = new Users(
                        rs.getInt("user_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("username"),
                        rs.getString("pword"),
                        null
                );
                //int for role fk
                int roleFK = rs.getInt("user_roles_id_fk");

                //dao obj for setting the role value
                RoleDAO rDAO = new RoleDAO();
                Role r = rDAO.getRoleByID(roleFK);
                u.setRole(r);
                return u;
            }

        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }
}
