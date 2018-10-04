package com.powerranger.comic.screen.detail;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Chapter;
import com.powerranger.comic.screen.content.ChapAdapter;
import com.powerranger.comic.screen.content.ComicContentActivity;

import java.util.ArrayList;
import java.util.List;

public class ChapterFragment extends Fragment implements ChapAdapter.OnClickItemChapterListener {

    private static final String BUNDLE_CHAPTER = "chapter";
    private List<Chapter> mChapters;
    private RecyclerView mRecyclerChapter;

    public static ChapterFragment newInstance(List<Chapter> chapters) {
        Bundle args = new Bundle();
        args.putParcelableArrayList(BUNDLE_CHAPTER, (ArrayList<? extends Parcelable>) chapters);
        ChapterFragment fragment = new ChapterFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_chapter, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerChapter = view.findViewById(R.id.recycler_chapter);
        mChapters = getChapter();
        ChapAdapter chapAdapter = new ChapAdapter(mChapters, this);
        mRecyclerChapter.setAdapter(chapAdapter);
    }

    private List<Chapter> getChapter() {
        return getArguments().getParcelableArrayList(BUNDLE_CHAPTER);
    }

    @Override
    public void onClick(int position) {
        Intent intent = ComicContentActivity
                .getComicContentIntent(getContext(), mChapters.get(position).getId(), position, mChapters);
        startActivity(intent);
    }
}
