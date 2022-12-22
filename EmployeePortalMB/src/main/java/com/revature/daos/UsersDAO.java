package com.revature.daos;

import com.revature.models.Role;
import com.revature.models.Users;
import com.revature.utils.ConnectionUtil;

import java.sql.*;
import java.util.ArrayList;

public class UsersDAO {

    //select all users
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
                ); //null because no jdbc obj for role so make it ourselves
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
    public Users getUserByID(int ID) {
        //try w/ resources block to create connection
        try (Connection conn = ConnectionUtil.getConnection()) {

            String sql = "select * from users where users_id = ?;";
            //going to the database - something SQL understands
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ID);
            //above 3 means retrieve every ID that meets the variable
            ResultSet rs = ps.executeQuery();
            //store query in the resultset

            // if there are results in the resultset, fill a role all-args constructor - make new role
            if (rs.next()){
                //changed to if statement since the while loop was being SUPREMELY pissy with me
                Users users = new Users(
                        rs.getInt("users_id"),
                        rs.getString("user_first_name"),
                        rs.getString("user_last_name"),
                        rs.getString("username"),
                        rs.getString("pword"),
                        null
                );
                int roleFK = rs.getInt("user_roles_id_fk");

                //role object to use id we got
                RoleDAO rDAO = new RoleDAO();
                Role r = rDAO.getRoleByID(roleFK);

                return users; //return role data to
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    //create a new acct with the project - "register feature" ---------------------------------------------------------------------------------------------------------
    //insert a user
    public boolean insertUsers(String username, String pword) {
        //DAO needs the connect
        try (Connection conn = ConnectionUtil.getConnection()) {
            String sql = "insert into users (username, pword, user_roles_id_fk) values (?, ?, 2);";

            //instantiate preparedstatement for dql and filling in var
            PreparedStatement ps = conn.prepareStatement(sql);

            //fill out the wildcards using the user object in the args
            ps.setString(1, username);
            ps.setString(2, pword);

            //execute update
            ps.executeUpdate();

            //no new objects created eg roledao etc = new roledao
            //bc a controller is returning data for me

            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    //delete a user?? -> update user role goes in the
}
