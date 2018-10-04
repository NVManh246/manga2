package com.powerranger.comic.data.api;

import com.powerranger.comic.data.model.Chapter;
import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.model.ComicResut;
import com.powerranger.comic.data.model.ImageResult;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ComicApi {
    @GET("manga")
    Call<List<Comic>> getMovies ();

    @GET("manga/{id_comic}/")
    Call<ComicResut> getComicResut(@Path("id_comic") String idComic);

    @GET("chapter/{id_chapter}")
    Call<ImageResult> getImages(@Path("id_chapter") String idChapter);

    @GET("search/{input}")
    Call<List<Comic>> searchComics(@Path("input") String input);
}
