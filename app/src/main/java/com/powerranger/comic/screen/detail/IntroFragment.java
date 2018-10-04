package com.powerranger.comic.screen.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powerranger.comic.R;

public class IntroFragment extends Fragment {

    private static final String BUNDLE_DES = "des";

    public static IntroFragment newInstance(String description) {
        Bundle args = new Bundle();
        args.putString(BUNDLE_DES, description);
        IntroFragment fragment = new IntroFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_intro, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle bundle = getArguments();
        String des = bundle.getString(BUNDLE_DES);
        TextView textIntro = view.findViewById(R.id.text_intro);
        textIntro.setText(des);
    }
}
