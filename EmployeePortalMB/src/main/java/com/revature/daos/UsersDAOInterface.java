package com.revature.daos;



import com.revature.models.Users;

import java.util.ArrayList;

public interface UsersDAOInterface {
    //make a class for each table in models
    //each table will get a DAO Interface and class

    //functions that UserDAO needs

    //select all users
    ArrayList<Users> getUsers();

    //insert new user, intended to send to the database
    Users insertUsers(Users user);

    //delete user, intend to send to the database
    boolean deleteUsersByID(int ID);
}
