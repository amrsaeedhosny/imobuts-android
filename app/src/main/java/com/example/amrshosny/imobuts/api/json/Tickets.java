package com.example.amrshosny.imobuts.api.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Tickets {
    @SerializedName("tickets")
    @Expose
    private List<Ticket> tickets = null;

    public List<Ticket> getTickets() {
        return tickets;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
    }
}
