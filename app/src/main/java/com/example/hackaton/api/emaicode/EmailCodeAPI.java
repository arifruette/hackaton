package com.example.hackaton.api.emaicode;

import com.example.hackaton.api.services.RetroFitService;
import com.example.hackaton.api.userapi.UserAPI;

public class EmailCodeAPI {
    private static UserAPI userAPI;

    private static UserAPI create(){
        return RetroFitService.getInstance().create(UserAPI.class);
    }

    public static UserAPI getInstance(){
        if(userAPI == null) {
            userAPI = create();
        }
        return userAPI;

    }
}
