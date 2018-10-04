package com.powerranger.comic.screen.favorite;

import android.content.Context;
import android.widget.ImageView;

import com.powerranger.comic.data.model.Comic;

import java.util.List;

public interface FavoriteContract {
    interface View {
        void showComics(List<Comic> comics);
        void showError();
        void showPopupMenu(Comic comic, boolean isFavorite, ImageView view);
        void showResultFavorite(String action, boolean b);
        Context getFavoriteContext();
    }

    interface Presenter {
        void getFavoriteComics();
        void checkFavorite(Comic comic, ImageView view);
        void handelFavorite(Comic comic, boolean isFavorite);
    }
}
