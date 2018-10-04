package com.powerranger.comic.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ImageResult {
    @SerializedName("manga")
    private Manga mManga;
    @SerializedName("images")
    private List<String> mImages;

    public ImageResult() {
    }

    public List<String> getImages() {
        return mImages;
    }

    public Manga getManga() {
        return mManga;
    }

    public class Manga {
        @SerializedName("id")
        private String mId;
        @SerializedName("title")
        private String mName;

        public Manga() {
        }

        public String getId() {
            return mId;
        }

        public String getName() {
            return mName;
        }
    }
}
