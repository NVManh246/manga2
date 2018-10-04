package com.powerranger.comic.screen.content;

import com.powerranger.comic.data.model.ImageResult;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.local.ComicsLocalDataSource;
import com.powerranger.comic.data.source.local.DBHelper;
import com.powerranger.comic.data.source.remote.ComicsRemoteDataSource;
import com.powerranger.comic.data.source.repository.ComicsRepository;
import com.powerranger.comic.ultis.Repository;

public class ContentPresenter implements ContentContract.Presenter{

    private ContentContract.View mView;
    private ComicsRepository mComicsRepository;

    public ContentPresenter(ContentContract.View view) {
        mView = view;
        mComicsRepository = Repository.getComicsRepository(mView.getContentContext());
    }

    @Override
    public void getUrlImagesByChapter(String idChapter) {
        mView.showProgress();
        mComicsRepository.getImages(idChapter, new OnCompleteListener<ImageResult>() {
            @Override
            public void onSuccess(ImageResult imageResult) {
                mView.showImages(imageResult);
                mView.hideProgress();
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.showError();
                mView.hideProgress();
            }
        });
    }
}
