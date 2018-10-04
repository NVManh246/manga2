package com.powerranger.comic.screen.home;

import com.powerranger.comic.data.model.Comic;

import android.content.Context;
import android.widget.ImageView;

import java.util.List;

public interface HomeContract {
    interface View {
        void showHotComics(List<Comic> comics);
        void showPopupMenu(Comic comic, boolean isFavorite, ImageView view);
        void showError();
        void showResultFavorite(String action, boolean b);
        Context getHomeContext();
    }

    interface Presenter {
        void getHotComics();
        void checkFavorite(Comic comic, ImageView view);
        void handelFavorite(Comic comic, boolean isFavorite);
    }
}
