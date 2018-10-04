package com.powerranger.comic.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicResut {
    @SerializedName("manga")
    private ComicDetail mComicDetail;
    @SerializedName("chapters")
    private List<Chapter> mChapters;

    public ComicResut() {
    }

    public ComicDetail getComicDetail() {
        return mComicDetail;
    }

    public List<Chapter> getChapters() {
        return mChapters;
    }
}
