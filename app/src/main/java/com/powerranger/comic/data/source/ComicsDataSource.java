package com.powerranger.comic.data.source;

import com.powerranger.comic.data.model.Comic;

public interface ComicsDataSource {

    interface ComicsRemoteDataSource {
        void getComic(OnCompleteListener callback);
        void getComicResult(String idComic, OnCompleteListener callback);
        void getImages(String idChapter, OnCompleteListener callback);
        void searchComics(String input, OnCompleteListener callback);
    }

    interface ComicsLocalDataSource {
        void addComic(Comic comic, OnCompleteListener callback);
        void removeComic(String comicId, OnCompleteListener callback);
        void checkFavorite(String comicId, OnCompleteListener callback);
        void getComics(OnCompleteListener callback);
    }
}
