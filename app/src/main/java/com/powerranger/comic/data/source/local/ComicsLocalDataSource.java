package com.powerranger.comic.data.source.local;

import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.source.ComicsDataSource;
import com.powerranger.comic.data.source.OnCompleteListener;

import java.util.List;

public class ComicsLocalDataSource implements ComicsDataSource.ComicsLocalDataSource {
    private static ComicsLocalDataSource sInstance;
    private DBHelper mDBHelper;

    private ComicsLocalDataSource(DBHelper dbHelper){
        mDBHelper = dbHelper;
    }

    public static ComicsLocalDataSource getInstance(DBHelper dbHelper) {
        if(sInstance == null) {
            sInstance = new ComicsLocalDataSource(dbHelper);
        }
        return sInstance;
    }

    @Override
    public void addComic(Comic comic, OnCompleteListener callback) {
        boolean resutl = mDBHelper.addComicToFavorite(comic);
        if(resutl) {
            callback.onSuccess(true);
        } else {
            callback.onSuccess(false);
        }
    }

    @Override
    public void removeComic(String comicId, OnCompleteListener callback) {
        boolean result = mDBHelper.removeFavoriteComic(comicId);
        if(result) {
            callback.onSuccess(true);
        } else {
            callback.onSuccess(false);
        }
    }

    @Override
    public void checkFavorite(String comicId, OnCompleteListener callback) {
        boolean result = mDBHelper.isFavorite(comicId);
        if(result) {
            callback.onSuccess(true);
        } else {
            callback.onSuccess(false);
        }
    }

    @Override
    public void getComics(OnCompleteListener callback) {
        List<Comic> comics = mDBHelper.getFavoriteComics();
        if(comics != null) {
            callback.onSuccess(comics);
        } else {
            callback.onFailure(null);
        }
    }
}
