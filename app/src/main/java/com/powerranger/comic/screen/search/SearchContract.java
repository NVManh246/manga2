package com.powerranger.comic.screen.search;

import android.content.Context;

import com.powerranger.comic.data.model.Comic;

import java.util.List;

public interface SearchContract {
    interface View {
        void showResult(List<Comic> comics);
        void showError();
        Context getSearchContext();
    }

    interface Presenter {
        void searchComics(String input);
    }
}
