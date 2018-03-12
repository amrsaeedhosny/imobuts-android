package com.example.amrshosny.imobuts.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by amrshosny on 11/03/18.
 */

public class FormResponse {
    @SerializedName("username")
    @Expose
    private String username = null;
    @SerializedName("email")
    @Expose
    private String email = null;
    @SerializedName("password")
    @Expose
    private String password = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
