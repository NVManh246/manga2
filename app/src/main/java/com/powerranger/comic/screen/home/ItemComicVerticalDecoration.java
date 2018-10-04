package com.powerranger.comic.screen.home;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

public class ItemComicVerticalDecoration extends RecyclerView.ItemDecoration {

    private int mSpacing;

    public ItemComicVerticalDecoration(int spacing) {
        mSpacing = spacing;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = mSpacing;
        outRect.bottom = mSpacing;

        if (parent.getChildLayoutPosition(view) == 0) {
            outRect.left = mSpacing * 2;
        } else {
            outRect.left = mSpacing;
        }

        if(parent.getChildLayoutPosition(view) == parent.getAdapter().getItemCount() - 1){
            outRect.right = mSpacing * 2;
        } else {
            outRect.right = mSpacing;
        }
    }
}
