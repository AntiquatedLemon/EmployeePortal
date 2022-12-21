package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO implements UsersDAOInterface {
    @Override
    public ArrayList<Users> getUsers() {

        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "select * from users;";

            //statement because no wildcards
            Statement s = conn.createStatement();

            //execute query to save them into the resultset
            ResultSet rs = s.executeQuery(sql);

            ArrayList <Users> usersList = new ArrayList();

            while (rs.next()) {
                Users u = new Users(
                        rs.getInt("user_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("username"),
                        rs.getString("pword"),
                        null
                );
                //come back here after role DAO
                //gets role value through fk
                int roleFK = rs.getInt("user_roles_id_fk");

                //role object to use id we got
                RoleDAO rDAO = new RoleDAO();
                Role r = rDAO.getRoleByID(roleFK);

                //user object setter to assign it a role
                u.setRole(r);

                //add to arraylist
                usersList.add(u);
            } //close while loop
            return usersList;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Users insertUsers(Users user) {
        //DAO needs the connect
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into users (user_first_name, user_last_name, username, pword, user_roles_id_fk values (?, ?, ?, ?, ?);";

            //instantiate prepstate for dql and filling in var
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill out the wildcards using the user object in the args
            ps.setString(1, user.getUser_first_name());
            ps.setString(2, user.getUser_last_name());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPword());
            ps.setInt(5, user.getRole_id_fk());

            //execute update
            ps.executeUpdate();

            //confirm that the user was added
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean deleteUsersByID(int ID) {
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "delete from users where user_id = ?;";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            ps.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
