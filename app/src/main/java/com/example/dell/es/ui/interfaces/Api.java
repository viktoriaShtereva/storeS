package com.example.dell.es.ui.interfaces;

import com.example.dell.es.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;


public interface Api {
    @POST("/upload/{new}.json")
    Call<User> setData(@Path("new") String s1, @Body User user);

}
