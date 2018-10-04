package com.powerranger.comic.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.powerranger.comic.R;
import com.powerranger.comic.data.model.Comic;

import java.util.List;

public class TrendingComicAdapter extends PagerAdapter{

    private List<Comic> mComics;

    public TrendingComicAdapter(List<Comic> comics) {
        mComics = comics;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(container.getContext())
                .inflate(R.layout.item_trending_comic, container, false);
        TextView text_title_comic = view.findViewById(R.id.text_name_comic);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return mComics != null ? mComics.size() : 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
