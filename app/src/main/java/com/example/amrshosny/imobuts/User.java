package com.example.amrshosny.imobuts;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amrshosny on 10/03/18.
 */

public class User {
    @SerializedName("username")
    @Expose
    private List<String> username = null;
    @SerializedName("email")
    @Expose
    private List<String> email = null;

    public List<String> getUsername() {
        return username;
    }

    public void setUsername(List<String> username) {
        this.username = username;
    }

    public List<String> getEmail() {
        return email;
    }

    public void setEmail(List<String> email) {
        this.email = email;
    }
}
