package com.powerranger.comic.screen.content;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Chapter;
import com.powerranger.comic.data.model.ImageResult;

import java.util.ArrayList;
import java.util.List;

public class ComicContentActivity extends AppCompatActivity implements View.OnClickListener,
        ChapAdapter.OnClickItemChapterListener, ContentContract.View {

    private static final String BUNDEL_ID_CHAPTER = "idchapter";
    private static final String BUNDEL_CHAPTERS = "chapters";
    private static final String BUNDEL_CHAPTER_POSITION = "position";
    private static final String EXTRA_DATA = "data";

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private ImageView mImageChapter;
    private LinearLayout mLinearChapter;
    private ImageView mImageProgress;
    private ProgressBar mProgressBar;

    private RecyclerView mRecyclerImage;
    private ImageAdapter mImageAdapter;
    private LinearLayoutManager mLayoutManagerImage;
    private TextView mTextTotalImage;
    private TextView mTextPositionImage;
    private ImageView mImageBack;

    private RecyclerView mRecyclerChapter;
    private LinearLayoutManager mLayoutChapterManager;
    private List<Chapter> mChapters;
    private ChapAdapter mChapAdapter;
    private int mCurrentPositionChapter;

    private ContentContract.Presenter mPresenter;

    public static Intent getComicContentIntent(
            Context context, String idChapter, int position, List<Chapter> chapters) {
        Intent intent = new Intent(context, ComicContentActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(BUNDEL_ID_CHAPTER, idChapter);
        bundle.putInt(BUNDEL_CHAPTER_POSITION, position);
        bundle.putParcelableArrayList(BUNDEL_CHAPTERS, (ArrayList<? extends Parcelable>) chapters);
        intent.putExtra(EXTRA_DATA, bundle);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comic_content);
        initView();
        mPresenter = new ContentPresenter(this);
        mDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout,
                R.string.drawer_open, R.string.drawer_close);
        mDrawerLayout.addDrawerListener(mDrawerToggle);
        getData();
        showImage();
        showChapter();
        mImageBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerClosed(View drawerView) {
                mLayoutChapterManager.scrollToPositionWithOffset(mCurrentPositionChapter, 100);
            }
        });
    }

    private void initView() {
        mDrawerLayout = findViewById(R.id.drawerlayout);
        mImageChapter = findViewById(R.id.image_chapter);
        mImageChapter.setOnClickListener(this);
        mLinearChapter = findViewById(R.id.container_chapter);
        mRecyclerImage = findViewById(R.id.recycler_image);
        mRecyclerChapter = findViewById(R.id.recycler_chapter);
        mTextTotalImage = findViewById(R.id.text_total_image);
        mTextPositionImage = findViewById(R.id.text_position_image);
        mImageBack = findViewById(R.id.image_back);
        mImageProgress = findViewById(R.id.image_progress);
        mProgressBar = findViewById(R.id.progress);
    }

    private void showImage() {
        mImageAdapter = new ImageAdapter(new ArrayList<String>());
        mRecyclerImage.setAdapter(mImageAdapter);
        mLayoutManagerImage = new LinearLayoutManager(this);
        mRecyclerImage.setLayoutManager(mLayoutManagerImage);

        mRecyclerImage.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                mTextPositionImage.setText(String.valueOf(mLayoutManagerImage.findLastVisibleItemPosition() + 1));
            }
        });
    }

    private void showChapter() {
        mChapAdapter = new ChapAdapter(mChapters, this);
        mRecyclerChapter.setAdapter(mChapAdapter);
        mLayoutChapterManager = new LinearLayoutManager(this);
        mRecyclerChapter.setLayoutManager(mLayoutChapterManager);
        mLayoutChapterManager.scrollToPositionWithOffset(mCurrentPositionChapter, 100);
        mChapAdapter.notifyDataSetChanged();
    }

    private void getData() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(EXTRA_DATA);
        String idChapter = bundle.getString(BUNDEL_ID_CHAPTER);
        mChapters = bundle.getParcelableArrayList(BUNDEL_CHAPTERS);
        mCurrentPositionChapter = bundle.getInt(BUNDEL_CHAPTER_POSITION);
        mChapters.get(mCurrentPositionChapter).setReading(true);
        mPresenter.getUrlImagesByChapter(idChapter);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.image_chapter:
                if(mDrawerLayout.isDrawerOpen(mLinearChapter)){
                    mDrawerLayout.closeDrawer(mLinearChapter);
                } else {
                    mDrawerLayout.openDrawer(mLinearChapter);
                }
                break;
        }
    }

    @Override
    public void onClick(int position) {
        mChapters.get(mCurrentPositionChapter).setReading(false);
        mChapAdapter.notifyItemChanged(mCurrentPositionChapter);
        mChapters.get(position).setReading(true);
        mChapAdapter.notifyItemChanged(position);
        mCurrentPositionChapter = position;
        mPresenter.getUrlImagesByChapter(mChapters.get(position).getId());
        mImageAdapter.setUrlImages(null);
        mDrawerLayout.closeDrawer(mLinearChapter);
    }

    @Override
    public void showImages(ImageResult imageResult) {
        mImageAdapter.setUrlImages(imageResult.getImages());
        mTextTotalImage.setText(imageResult.getImages().size() + "");
        TextView textNameComic = findViewById(R.id.text_name_comic);
        textNameComic.setText(imageResult.getManga().getName());
    }

    @Override
    public void showError() {
        Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgress() {
        mImageProgress.setVisibility(View.VISIBLE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mImageProgress.setVisibility(View.GONE);
                mProgressBar.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    public Context getContentContext() {
        return this;
    }
}
