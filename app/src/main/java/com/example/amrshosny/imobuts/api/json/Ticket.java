package com.example.amrshosny.imobuts.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Ticket {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("code")
    @Expose
    private String code;
    @SerializedName("date")
    @Expose
    private String date;
    @SerializedName("price")
    @Expose
    private Integer price;
    @SerializedName("viewed")
    @Expose
    private Boolean viewed;

    public Ticket(){
    }

    public Ticket(Integer id, String code, String date, Integer price, Boolean viewed) {
        this.id = id;
        this.code = code;
        this.date = date;
        this.price = price;
        this.viewed = viewed;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Boolean getViewed() {
        return viewed;
    }

    public void setViewed(Boolean viewed) {
        this.viewed = viewed;
    }
}
