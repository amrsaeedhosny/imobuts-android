package com.example.amrshosny.imobuts;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface ApplicationApi {
    String BASE_URL = "https://smartbussystem.000webhostapp.com/api/";

    @FormUrlEncoded
    @POST("signUp")
    Call<JsonResponse<FormResponse>> signUp(@Field("username") String username,
                                            @Field("email") String email,
                                            @Field("password") String password);
    @FormUrlEncoded
    @POST("signIn")
    Call<JsonResponse<FormResponse>> signIn(@Field("username") String username,
                                            @Field("password") String password);
}
