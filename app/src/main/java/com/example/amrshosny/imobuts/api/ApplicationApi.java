package com.example.amrshosny.imobuts.api;

import com.example.amrshosny.imobuts.api.json.Form;
import com.example.amrshosny.imobuts.api.json.JsonResponse;
import com.example.amrshosny.imobuts.api.json.Ticket;
import com.example.amrshosny.imobuts.api.json.Tickets;
import com.example.amrshosny.imobuts.api.json.User;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApplicationApi {
    String BASE_URL = "https://imobuts.herokuapp.com/api/";

    @FormUrlEncoded
    @POST("signUp")
    Call<JsonResponse<Form>> signUp(@Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @FormUrlEncoded
    @POST("signIn")
    Call<JsonResponse<Form>> signIn(@Field("username") String username,
                                    @Field("password") String password);

    @GET("resetPassword")
    Call<JsonResponse<Form>> resetPassword(@Query("email") String email);

    @GET("getProfile")
    Call<JsonResponse<User>> getProfile(@Query("token") String token);

    @FormUrlEncoded
    @POST("updateProfile")
    Call<JsonResponse<Form>> updateProfile(@Query("token") String token,
                                    @Field("username") String username,
                                    @Field("email") String email,
                                    @Field("password") String password);

    @GET("tickets")
    Call<JsonResponse<Tickets>> getTickets(@Query("token") String token);

    @GET("ticketDetails")
    Call<JsonResponse<Ticket>> getTicketDetails(@Query("id") Integer id,
                                                @Query("token") String token);

}
