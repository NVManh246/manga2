package com.powerranger.comic.data.api;

public class ApiFactory {
    public static ComicApi getApi(){
        return ApiConfig.getRetrofit().create(ComicApi.class);
    }
}
