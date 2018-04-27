package com.example.amrshosny.imobuts.components.content.tickets;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.amrshosny.imobuts.R;
import com.example.amrshosny.imobuts.api.ApiController;
import com.example.amrshosny.imobuts.api.json.JsonResponse;
import com.example.amrshosny.imobuts.api.json.Ticket;
import com.example.amrshosny.imobuts.api.json.User;
import com.example.amrshosny.imobuts.components.content.tickets.TicketsAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TicketsFragment extends Fragment {
    View view;
    private ListView mListView;
    ArrayList<Ticket> tickets;
    SharedPreferences sharedPreferences;
    String token;

    public TicketsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_tickets, container, false);
        mListView = (ListView) view.findViewById(R.id.tickets_listview);
        sharedPreferences = this.getActivity().getSharedPreferences("auth", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("token", null);

        ApiController.getApi()
                .getTickets(token)
                .enqueue(new Callback<JsonResponse<ArrayList<Ticket>>>() {
                    @Override
                    public void onResponse(Call<JsonResponse<ArrayList<Ticket>>> call, Response<JsonResponse<ArrayList<Ticket>>> response) {
                        if(response.body().getSuccess()){
                            tickets = response.body().getResponse();
                            TicketsAdapter adapter = new TicketsAdapter(getActivity(), tickets);
                            mListView.setAdapter(adapter);
                        }
                    }

                    @Override
                    public void onFailure(Call<JsonResponse<ArrayList<Ticket>>> call, Throwable t) {

                    }
                });

        return view;
    }
}
