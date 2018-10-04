package com.powerranger.comic.screen.kind;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

public class SnapLinearLayoutManger extends LinearLayoutManager  {

    private int mWidthDv;

    public SnapLinearLayoutManger(Context context, int orientation, boolean reverseLayout, int widthDv) {
        super(context, orientation, reverseLayout);
        mWidthDv = widthDv;
    }

    public void scrollToPositionWithOffset(int position) {
        int itemWidth = this.findViewByPosition(position).getWidth();
        int offset = (mWidthDv/2) - (itemWidth/2);
        scrollToPositionWithOffset(position, offset);
    }
}
