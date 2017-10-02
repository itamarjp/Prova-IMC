package com.professorangoti.imc;


import com.professorangoti.imc.UserClass;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiEndpoint {
    @GET("json")
    //Call<UserClass> ObterPosts(@Path("id") int userID);
    Call<UserClass> ObterPosts();
}