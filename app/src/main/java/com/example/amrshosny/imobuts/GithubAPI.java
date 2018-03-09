package com.example.amrshosny.imobuts;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by amrshosny on 16/02/18.
 */

public interface GithubAPI {
    @GET("user")
    Call<User> getUserById(@Query("id") Integer id);

}
