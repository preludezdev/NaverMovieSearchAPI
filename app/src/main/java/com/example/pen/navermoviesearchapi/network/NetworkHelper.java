package com.example.pen.navermoviesearchapi.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {
    String API_URL = "https://openapi.naver.com/";

    private static NetworkHelper instance = null;

    public static NetworkHelper getInstance(){
        if(instance == null) instance = new NetworkHelper();
        return instance;
    }

    public Retrofit retrofit;
    public movieAPIService movieAPIService;

    private NetworkHelper(){
        retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)  //baseURL
                .addConverterFactory(GsonConverterFactory.create()) //JSON 컨버터
                .build();

        movieAPIService = retrofit.create(movieAPIService.class);
    }
}
