package com.example.hackaton.api.services;

import java.net.URL;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

public class RetroFitService {
    private static final String Url = "https://medic.madskill.ru/api/";
    private static Retrofit retrofit;

    private static Retrofit create(){
        return new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .baseUrl(Url)
                .build();
    }

    public static Retrofit getInstance(){
        if(retrofit == null) {
            retrofit = create();
        }
        return retrofit;

    }
}
