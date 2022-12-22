package com.revature.models;

public class Users {

    //users have roles
    //come back later :)

    private int user_id;
    private String user_first_name;
    private String user_last_name;
    private String username;
    private String pword;
    //users have role objects instead of just a fk
    private Role role;
    private int user_roles_id_fk; //makes Postman inserts easier

    //no-args, flexibility, have an user to fill in later
    public Users(){
    }


    //all-args
    public Users(int user_id, String user_first_name, String user_last_name, String username, String pword, Role role) {
        this.user_id = user_id;
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.username = username;
        this.pword = pword;
        this.role = role;
    }


    //all-args sans id
    public Users(String user_first_name, String user_last_name, String username, String pword, Role role) {
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.username = username;
        this.pword = pword;
        this.role = role;
    }

    //another all-args no id but to help with http post request to insert users
    //no need to specify an entire Role object
    public Users(String user_first_name, String user_last_name, String username, String pword, int user_roles_id_fk) {
        this.user_first_name = user_first_name;
        this.user_last_name = user_last_name;
        this.username = username;
        this.pword = pword;
        this.user_roles_id_fk = user_roles_id_fk;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
        this.user_first_name = user_first_name;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
        this.user_last_name = user_last_name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPword() {
        return pword;
    }

    public void setPword(String pword) {
        this.pword = pword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public int getRole_id_fk() {
        return user_roles_id_fk;
    }

    public void setRole_id_fk(int user_roles_id_fk) {
        this.user_roles_id_fk = user_roles_id_fk;
    }

    public String toString(){
        return "Users{" +
                "user_id" + user_id +
                ", user_first_name='" + user_first_name + '\'' +
                ", user_last_name='" + user_last_name + '\'' +
                ", role=" + role +
                ", username='" + username + '\'' +
                ", password='" + pword + '\'' +
                '}';
    }
}
