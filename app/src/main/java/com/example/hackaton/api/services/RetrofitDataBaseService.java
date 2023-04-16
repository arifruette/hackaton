package com.example.hackaton.api.services;

import com.example.hackaton.api.userapi.PostApi;
import com.example.hackaton.api.userapi.UserAPI;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitDataBaseService {
    private static RetrofitDataBaseService instance = null;
    private PostApi myApi;

    private RetrofitDataBaseService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(PostApi.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        myApi = retrofit.create(PostApi.class);
    }

    public static synchronized RetrofitDataBaseService getInstance() {
        if (instance == null) {
            instance = new RetrofitDataBaseService();
        }
        return instance;
    }

    public PostApi getMyApi() {
        return myApi;
    }
}
