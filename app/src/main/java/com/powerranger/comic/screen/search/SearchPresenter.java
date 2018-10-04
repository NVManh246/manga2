package com.powerranger.comic.screen.search;

import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.source.OnCompleteListener;
import com.powerranger.comic.data.source.repository.ComicsRepository;
import com.powerranger.comic.ultis.Repository;

import java.util.List;

public class SearchPresenter implements SearchContract.Presenter {

    private SearchContract.View mView;
    private ComicsRepository mComicsRepository;

    public SearchPresenter(SearchContract.View view) {
        mView = view;
        mComicsRepository = Repository.getComicsRepository(mView.getSearchContext());
    }

    @Override
    public void searchComics(String input) {
        mComicsRepository.searchComics(input, new OnCompleteListener<List<Comic>>() {
            @Override
            public void onSuccess(List<Comic> comics) {
                mView.showResult(comics);
            }

            @Override
            public void onFailure(Throwable throwable) {
                mView.showError();
            }
        });
    }
}
