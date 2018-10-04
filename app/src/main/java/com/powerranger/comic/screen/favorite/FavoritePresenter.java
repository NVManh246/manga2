package com.powerranger.comic.screen.favorite;

import android.widget.ImageView;
import android.widget.Toast;

import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.repository.ComicsRepository;
import com.powerranger.comic.ultis.Repository;

import java.util.List;

public class FavoritePresenter implements FavoriteContract.Presenter {

    private FavoriteContract.View mView;
    private ComicsRepository mComicsRepository;

    public FavoritePresenter(FavoriteContract.View view) {
        mView = view;
        mComicsRepository = Repository.getComicsRepository(mView.getFavoriteContext());
    }

    @Override
    public void getFavoriteComics() {
        mComicsRepository.getComics(new OnCompleteListener<List<Comic>>() {
            @Override
            public void onSuccess(List<Comic> comics) {
                mView.showComics(comics);
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
                Toast.makeText(mView.getFavoriteContext(), "Error", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mView.getFavoriteContext(), "Error", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(mView.getFavoriteContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }
}
