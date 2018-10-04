package com.powerranger.comic.screen.content;

import android.widget.SeekBar;

public abstract class OnSeekBarChange implements SeekBar.OnSeekBarChangeListener {
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
        onChange(i);
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }

    public abstract void onChange(int i);
}
