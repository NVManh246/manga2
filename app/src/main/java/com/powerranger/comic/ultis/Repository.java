package com.powerranger.comic.ultis;

import android.content.Context;

import com.powerranger.comic.data.source.local.ComicsLocalDataSource;
import com.powerranger.comic.data.source.local.DBHelper;
import com.powerranger.comic.data.source.remote.ComicsRemoteDataSource;
import com.powerranger.comic.data.source.repository.ComicsRepository;

public class Repository {
    public static ComicsRepository getComicsRepository(Context context){
        return ComicsRepository.getInstance(ComicsRemoteDataSource.getInstance(),
                ComicsLocalDataSource.getInstance(DBHelper.getInstance(context)));
    }
}
