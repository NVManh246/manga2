package com.powerranger.comic.screen.home;

import android.support.v4.view.ViewPager;

public abstract class PagerChangeListener implements ViewPager.OnPageChangeListener{

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        onPageChange(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    public abstract void onPageChange(int position);
}
