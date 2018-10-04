package com.powerranger.comic.screen.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.nex3z.flowlayout.FlowLayout;
import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Chapter;
import com.powerranger.comic.data.model.Comic;
import com.powerranger.comic.data.model.ComicDetail;
import com.powerranger.comic.data.model.ComicResut;

import java.util.ArrayList;
import java.util.List;

public class ComicDetailActivity extends AppCompatActivity implements DetailContract.View{

    public static final String EXTRA_COMIC = "comic";

    private DetailContract.Presenter mPresenter;

    private TabLayout mTabLayout;
    private ViewPager mViewPager;
    private RecyclerView mRecyclerKind;
    private String mIdComic;
    private Comic mComic;
    private TextView mTextNameComic;
    private TextView mTextStatus;
    private TextView mTextUpdate;
    private FlowLayout mFlowAuthors;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_detail);
        mPresenter = new DetailPresenter(this);
        mIdComic = getIdComic();
        mPresenter.getDetailComic(mIdComic);
        initView();
        setupView();
    }

    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                supportFinishAfterTransition();
            }
        });

        mTabLayout = findViewById(R.id.tablayout);
        mViewPager = findViewById(R.id.viewpager);
        mRecyclerKind = findViewById(R.id.recycler_kind);
        mTextNameComic = findViewById(R.id.text_name_comic);
        mTextStatus = findViewById(R.id.text_status);
        mTextUpdate = findViewById(R.id.text_update);
        mFlowAuthors = findViewById(R.id.flow_author);
    }

    private void setupView() {

    }

    private void setupViewPager(ComicDetail comicDetail, List<Chapter> chapters){
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(IntroFragment.newInstance(comicDetail.getDescription()));
        fragments.add(ChapterFragment.newInstance(chapters));
        List<String> titles = new ArrayList<>();
        titles.add("Giới thiệu");
        titles.add("Chapter");

        DetailPagerAdapter detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(),
                fragments, titles);
        mViewPager.setAdapter(detailPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    private String getIdComic() {
        Intent intent = getIntent();
        return intent.getStringExtra(EXTRA_COMIC);
    }

    @Override
    public void showDetailComic(ComicResut comicResut) {
        ComicDetail comicDetail = comicResut.getComicDetail();
        mTextNameComic.setText(comicDetail.getTitle());
        mTextStatus.setText(comicDetail.getStatus()
                .length() != 0 ? comicDetail.getStatus() : getString(R.string.updating));
        mTextUpdate.setText(comicDetail.getUpdate()
                .length() != 0 ? comicDetail.getUpdate().split(" ")[1] : getString(R.string.updating));
        KindAdapter kindAdapter = new KindAdapter(comicDetail.getKinds());
        mRecyclerKind.setAdapter(kindAdapter);
        setupViewPager(comicDetail, comicResut.getChapters());

        List<String> authors = comicDetail.getAuthors();
        if(authors.size() != 0){
            mFlowAuthors.removeAllViews();
            for(String author : authors) {
                TextView text = new TextView(this);
                text.setText(author);
                text.setTextColor(getResources().getColor(R.color.colorPrimary));
                text.setTextSize(13);
                mFlowAuthors.addView(text);
            }
        } else {
            TextView text = new TextView(this);
            text.setText(getString(R.string.updating));
            text.setTextColor(getResources().getColor(R.color.color_dark_gray));
            text.setTextSize(13);
            mFlowAuthors.removeAllViews();
            mFlowAuthors.addView(text);
        }
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Connect error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public Context getDetailContext() {
        return this;
    }
}
