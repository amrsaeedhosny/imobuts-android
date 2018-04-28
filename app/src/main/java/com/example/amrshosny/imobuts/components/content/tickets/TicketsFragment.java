package com.example.amrshosny.imobuts.components.content.tickets;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.amrshosny.imobuts.R;
import com.example.amrshosny.imobuts.api.ApiController;
import com.example.amrshosny.imobuts.api.json.JsonResponse;
import com.example.amrshosny.imobuts.api.json.Ticket;
import com.example.amrshosny.imobuts.api.json.Tickets;
import com.example.amrshosny.imobuts.components.buyticket.BuyTicket;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketsFragment extends Fragment {
    View view;
    Button buyTicket;
    ListView mListView;
    List<Ticket> tickets;
    Ticket ticket;
    SharedPreferences sharedPreferences;
    String token;
    Dialog ticketDetails;

    public TicketsFragment() {
        tickets = new ArrayList<Ticket>();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        buyTicket = view.findViewById(R.id.buy_ticket);
        mListView = (ListView) view.findViewById(R.id.tickets_listview);
        sharedPreferences = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);
        ticketDetails = new Dialog(getActivity());
        ticketDetails.requestWindowFeature(Window.FEATURE_NO_TITLE);
        ticketDetails.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        ticketDetails.setContentView(R.layout.ticket_details);

        buyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), BuyTicket.class);
                startActivity(intent);
            }
        });

        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                getTicketDetailsApi(tickets.get(i).getId());
                TextView code = (TextView) ticketDetails.findViewById(R.id.code);
                code.setText(ticket.getCode());
                TextView date = (TextView) ticketDetails.findViewById(R.id.date);
                date.setText(ticket.getDate());
                TextView price = (TextView) ticketDetails.findViewById(R.id.price);
                price.setText(ticket.getPrice().toString() + " Egp");
                ticketDetails.show();
            }
        });

        getTicketsApi();

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        getTicketsApi();
    }

    void getTicketsApi(){
        ApiController.getApi()
                .getTickets(token)
                .enqueue(new Callback<JsonResponse<Tickets>>() {
                    @Override
                    public void onResponse(Call<JsonResponse<Tickets>> call, Response<JsonResponse<Tickets>> response) {
                        if(response.body().getSuccess()){
                            tickets = response.body().getResponse().getTickets();
                            TicketsAdapter adapter = new TicketsAdapter(getActivity(), tickets);
                            mListView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonResponse<Tickets>> call, Throwable t) {

                    }
                });
    }

    void getTicketDetailsApi(Integer id){
        ticket = new Ticket();
        ApiController.getApi()
                .getTicketDetails(token, id)
                .enqueue(new Callback<JsonResponse<Ticket>>() {
                    @Override
                    public void onResponse(Call<JsonResponse<Ticket>> call, Response<JsonResponse<Ticket>> response) {
                        if(response.body().getSuccess()){
                            ticket = response.body().getResponse();
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonResponse<Ticket>> call, Throwable t) {

                    }
                });
    }
}
