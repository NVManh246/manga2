package com.powerranger.comic.data.source.remote;

import com.powerranger.comic.data.api.ApiFactory;
import com.powerranger.comic.data.api.ComicApi;
import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.model.ComicResut;
import com.powerranger.comic.data.model.ImageResult;
import com.powerranger.comic.data.source.ComicsDataSource;
import com.powerranger.comic.data.source.OnCompleteListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ComicsRemoteDataSource implements ComicsDataSource.ComicsRemoteDataSource {

    private static ComicsRemoteDataSource sInstance;
    private ComicApi mComicApi;

    private ComicsRemoteDataSource(){
        mComicApi = ApiFactory.getApi();
    }

    public static ComicsRemoteDataSource getInstance() {
        if(sInstance == null) {
            sInstance = new ComicsRemoteDataSource();
        }
        return sInstance;
    }

    @Override
    public void getComic(final OnCompleteListener callback) {
        Call<List<Comic>> call = mComicApi.getMovies();
        call.enqueue(new Callback<List<Comic>>() {
            @Override
            public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Comic>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getComicResult(String idComic, final OnCompleteListener callback) {
        Call<ComicResut> call =  mComicApi.getComicResut(idComic);
        call.enqueue(new Callback<ComicResut>() {
            @Override
            public void onResponse(Call<ComicResut> call, Response<ComicResut> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ComicResut> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void getImages(String idChapter, final OnCompleteListener callback) {
        Call<ImageResult> call = mComicApi.getImages(idChapter);
        call.enqueue(new Callback<ImageResult>() {
            @Override
            public void onResponse(Call<ImageResult> call, Response<ImageResult> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ImageResult> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    @Override
    public void searchComics(String input, final OnCompleteListener callback) {
        Call<List<Comic>> call = mComicApi.searchComics(input);
        call.enqueue(new Callback<List<Comic>>() {
            @Override
            public void onResponse(Call<List<Comic>> call, Response<List<Comic>> response) {
                callback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<List<Comic>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
