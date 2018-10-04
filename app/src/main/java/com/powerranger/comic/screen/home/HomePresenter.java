package com.powerranger.comic.screen.home;

import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.local.ComicsLocalDataSource;
import com.powerranger.comic.data.source.local.DBHelper;
import com.powerranger.comic.data.source.remote.ComicsRemoteDataSource;
import com.powerranger.comic.data.source.repository.ComicsRepository;
import com.powerranger.comic.ultis.Repository;

import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.List;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;
    private ComicsRepository mComicsRepository;

    public HomePresenter(HomeContract.View view) {
        mView = view;
        mComicsRepository = Repository.getComicsRepository(mView.getHomeContext());
    }

    @Override
    public void getHotComics() {
        mComicsRepository.getComic(new OnCompleteListener<List<Comic>>() {

            @Override
            public void onSuccess(List<Comic> comics) {
                mView.showHotComics(comics);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.showError();
            }
        });
    }

    @Override
    public void checkFavorite(final Comic comic, final ImageView view) {
        mComicsRepository.checkFavorite(comic.getId(), new OnCompleteListener<Boolean>() {

            @Override
            public void onSuccess(Boolean aBoolean) {
                mView.showPopupMenu(comic, aBoolean, view);
            }

            @Override
            public void onFailure(Throwable throwable) {
                Toast.makeText(mView.getHomeContext(), "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void handelFavorite(Comic comic, boolean isFavorite) {
        if(isFavorite) {
            mComicsRepository.removeComic(comic.getId(), new OnCompleteListener<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    mView.showResultFavorite("REMOVE", aBoolean);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Toast.makeText(mView.getHomeContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        } else {
            mComicsRepository.addComic(comic, new OnCompleteListener<Boolean>() {
                @Override
                public void onSuccess(Boolean aBoolean) {
                    mView.showResultFavorite("ADD", aBoolean);
                }

                @Override
                public void onFailure(Throwable throwable) {
                    Toast.makeText(mView.getHomeContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
