package com.powerranger.comic.screen.kind;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.powerranger.comic.R;

import java.util.ArrayList;
import java.util.List;

public class KindFragment extends Fragment  {

    private RecyclerView mRecyclerKind;
    private SnapLinearLayoutManger mLayoutManager;
    private int mOldPosition;

    public static KindFragment newInstance() {
        KindFragment fragment = new KindFragment();
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_kind, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerKind = view.findViewById(R.id.recycler);


    }
}
