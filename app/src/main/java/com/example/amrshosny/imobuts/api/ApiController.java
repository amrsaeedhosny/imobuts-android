package com.example.amrshosny.imobuts.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiController {
    private static ApiController apiController;
    private static ApplicationApi applicationApi;

    private ApiController(){
        Gson gson = new GsonBuilder().setLenient().create();
        Retrofit retrofit = new Retrofit
                .Builder()
                .baseUrl(applicationApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        applicationApi = retrofit.create(ApplicationApi.class);
    }

    public static ApplicationApi getApi(){
        if(apiController == null){
            apiController = new ApiController();
        }
        return apiController.applicationApi;
    }

}
