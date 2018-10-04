package com.powerranger.comic.screen.detail;

import android.content.Context;

import com.powerranger.comic.data.model.ComicResut;

public interface DetailContract {
    interface View {
        void showDetailComic(ComicResut comicResut);
        void showError();
        Context getDetailContext();
    }

    interface Presenter {
        void getDetailComic(String idComic);
    }
}
