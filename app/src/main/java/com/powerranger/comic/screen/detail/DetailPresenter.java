package com.powerranger.comic.screen.detail;

import com.powerranger.comic.data.model.ComicResut;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.local.ComicsLocalDataSource;
import com.powerranger.comic.data.source.local.DBHelper;
import com.powerranger.comic.data.source.remote.ComicsRemoteDataSource;
import com.powerranger.comic.data.source.repository.ComicsRepository;
import com.powerranger.comic.ultis.Repository;

public class DetailPresenter implements DetailContract.Presenter {

    private DetailContract.View mView;
    private ComicsRepository mComicsRepository;

    public DetailPresenter(DetailContract.View view) {
        mView = view;
        mComicsRepository = Repository.getComicsRepository(mView.getDetailContext());
    }

    @Override
    public void getDetailComic(String idComic) {
        mComicsRepository.getComicResult(idComic, new OnCompleteListener<ComicResut>() {
            @Override
            public void onSuccess(ComicResut comicResut) {
                mView.showDetailComic(comicResut);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.showError();
            }
        });
    }
}
