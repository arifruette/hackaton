package com.example.hackaton.api.services;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetrofitDataBaseService {
    private static RetrofitDataBaseService instance = null;
    private Api myApi;

    private RetrofitDataBaseService() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(Api.BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
        myApi = retrofit.create(Api.class);
    }

    public static synchronized RetrofitDataBaseService getInstance() {
        if (instance == null) {
            instance = new RetrofitDataBaseService();
        }
        return instance;
    }

    public Api getMyApi() {
        return myApi;
    }
}
