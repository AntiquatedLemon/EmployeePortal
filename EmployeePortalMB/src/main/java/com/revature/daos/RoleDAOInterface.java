package com.revature.daos;

import com.revature.models.Role;

public interface RoleDAOInterface {
    //return Role given an id from the database
    Role getRoleByID(int ID);

    //just in case I want to update the role title
    boolean updateRoleTitle(String title);
}
