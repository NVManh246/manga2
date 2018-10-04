package com.powerranger.comic.screen.content;

import android.content.Context;

import com.powerranger.comic.data.model.ImageResult;

public interface ContentContract {
    interface View {
        void showImages(ImageResult imageResult);
        void showError();
        void showProgress();
        void hideProgress();
        Context getContentContext();
    }

    interface Presenter {
        void getUrlImagesByChapter(String idChapter);
    }
}
