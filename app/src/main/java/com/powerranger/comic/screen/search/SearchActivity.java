package com.powerranger.comic.screen.search;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Comic;

import java.util.ArrayList;
import java.util.List;

public class SearchActivity extends AppCompatActivity implements SearchContract.View {

    private ImageView mImageBack;
    private EditText mEditInput;
    private RecyclerView mRecyclerResult;
    private SearchContract.Presenter mPresenter;
    private List<Comic> mComics;

    public static Intent getSearchItent(Context context) {
        Intent intent = new Intent(context, SearchActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        mImageBack = findViewById(R.id.image_back);
        mEditInput = findViewById(R.id.edit_title);
        mRecyclerResult = findViewById(R.id.recycler_result);
        mPresenter = new SearchPresenter(this);
        mComics = new ArrayList<>();
        ComicAdapter comicAdapter = new ComicAdapter(mComics);


        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mEditInput.addTextChangedListener(new OnTextChangedListener() {
            @Override
            public void onTextChanged(String s) {
                Log.d("kiemtra", s);
                //mPresenter.searchComics(s);
            }
        });
    }

    @Override
    public void showResult(List<Comic> comics) {

    }

    @Override
    public void showError() {

    }

    @Override
    public Context getSearchContext() {
        return null;
    }
}
