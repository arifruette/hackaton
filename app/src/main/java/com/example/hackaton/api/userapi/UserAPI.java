package com.example.hackaton.api.userapi;

import com.example.hackaton.model.Banner;
import com.example.hackaton.model.Order;
import com.example.hackaton.model.User;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface UserAPI {
    @Headers({
            "Accept: application/json"
    })
    @POST("sendCode")
    Call<ResponseBody> sendCode(@Header("email") String email);

    @Headers({
            "Accept: application/json"

    })
    @POST("signin")
    Call<ResponseBody> checkCode(@Header("email") String email, @Header("code") String code);


    @GET("catalog")
    Call<List<Order>>  getCatalog();


    @GET("news")
    Call<List<Banner>>  getBanners();

}
