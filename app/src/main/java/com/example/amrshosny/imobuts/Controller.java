package com.example.amrshosny.imobuts;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by amrshosny on 16/02/18.
 */

public class Controller implements Callback<User> {
    static final String BASE_URL = "https://api.github.com/";

    public void start() {
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        GithubAPI githubAPI = retrofit.create(GithubAPI.class);
        Call<User> call = githubAPI.getUserById(1);
        call.enqueue(this);
        Log.i("HI", "BYE");
    }

    @Override
    public void onResponse(Call<User> call, Response<User> response) {
        if(response.isSuccessful()){
            User user = response.body();
            Log.i("myInfo",user.getLogin());
        }
        else {
            Log.i("myInfo", response.errorBody().toString());
        }
    }

    @Override
    public void onFailure(Call<User> call, Throwable t) {
        t.printStackTrace();
    }
}
