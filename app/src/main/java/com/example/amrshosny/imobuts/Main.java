package com.example.amrshosny.imobuts;

import java.text.Normalizer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Main {

    public static void main (String [] args){
        ApiController.getApi().signUp("amrshosny","amrshosny@gmail.com","amrshosny").enqueue(new Callback<JsonResponse<FormResponse>>() {
            @Override
            public void onResponse(Call<JsonResponse<FormResponse>> call, Response<JsonResponse<FormResponse>> response) {
                if(response.isSuccessful()){

                    System.out.println(response.body().getSuccess());
                }
                else {
                    System.out.println("Failure");
                }
            }

            @Override
            public void onFailure(Call<JsonResponse<FormResponse>> call, Throwable t) {
                System.out.print("LUntitled DocumentOL");
            }

        });


    }
}
