package com.powerranger.comic.data.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.powerranger.comic.ultis.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfig {

    public static final int READ_TIMEOUT = 5000;
    public static final int WRITE_TIMEOUT = 5000;
    public static final int CONNECT_TIMEOUT = 5000;
    private static Retrofit sRetrofit;

    public static Retrofit getRetrofit() {
        if(sRetrofit == null) {
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(READ_TIMEOUT, TimeUnit.MILLISECONDS)
                    .writeTimeout(WRITE_TIMEOUT, TimeUnit.MILLISECONDS)
                    .connectTimeout(CONNECT_TIMEOUT, TimeUnit.MILLISECONDS)
                    .retryOnConnectionFailure(true)
                    .build();
            Gson gson = new GsonBuilder().setLenient().create();
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(Constants.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build();
        }
        return sRetrofit;
    }
}
