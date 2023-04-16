package com.example.hackaton.api.userapi;

import com.example.hackaton.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PostApi {
    String BASE_URL = "http://192.168.0.109:8080/";


    @POST("user")
    Call<User> addUser(@Body User user);
}
