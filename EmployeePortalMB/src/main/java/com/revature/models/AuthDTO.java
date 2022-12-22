package com.revature.models;
//data transfer object
public class AuthDTO {
    private String username;
    private String pword;

    public AuthDTO(String username, String pword) {
        this.username = username;
        this.pword = pword;
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
}
