package com.powerranger.comic.data.source.repository;

import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.source.ComicsDataSource;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.local.ComicsLocalDataSource;
import com.powerranger.comic.data.source.remote.ComicsRemoteDataSource;

public class ComicsRepository implements ComicsDataSource.ComicsRemoteDataSource,
        ComicsDataSource.ComicsLocalDataSource {

    private static ComicsRepository sInstance;
    private ComicsDataSource.ComicsRemoteDataSource mComicsRemoteDataSource;
    private ComicsDataSource.ComicsLocalDataSource mComicsLocalDataSource;

    private ComicsRepository(ComicsRemoteDataSource comicsRemoteDataSource,
                             ComicsLocalDataSource comicsLocalDataSource) {
        mComicsRemoteDataSource = comicsRemoteDataSource;
        mComicsLocalDataSource = comicsLocalDataSource;
    }

    public static ComicsRepository getInstance(ComicsRemoteDataSource comicsRemoteDataSource,
                                               ComicsLocalDataSource comicsLocalDataSource) {
        if(sInstance == null) {
            sInstance = new ComicsRepository(comicsRemoteDataSource, comicsLocalDataSource);
        }
        return sInstance;
    }

    @Override
    public void getComic(OnCompleteListener callback) {
        mComicsRemoteDataSource.getComic(callback);
    }

    @Override
    public void getComicResult(String idComic, OnCompleteListener callback) {
        mComicsRemoteDataSource.getComicResult(idComic, callback);
    }

    @Override
    public void getImages(String idChapter, OnCompleteListener callback) {
        mComicsRemoteDataSource.getImages(idChapter, callback);
    }

    @Override
    public void searchComics(String input, OnCompleteListener callback) {
        mComicsRemoteDataSource.searchComics(input, callback);
    }

    @Override
    public void addComic(Comic comic, OnCompleteListener callback) {
        mComicsLocalDataSource.addComic(comic, callback);
    }

    @Override
    public void removeComic(String comicId, OnCompleteListener callback) {
        mComicsLocalDataSource.removeComic(comicId, callback);
    }

    @Override
    public void checkFavorite(String comicId, OnCompleteListener callback) {
        mComicsLocalDataSource.checkFavorite(comicId, callback);
    }

    @Override
    public void getComics(OnCompleteListener callback) {
        mComicsLocalDataSource.getComics(callback);
    }
}
