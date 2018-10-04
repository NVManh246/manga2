package com.powerranger.comic.screen.search;

import android.text.Editable;
import android.text.TextWatcher;

public abstract class OnTextChangedListener implements TextWatcher {
    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        onTextChanged(String.valueOf(charSequence));
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    public abstract void onTextChanged(String s);
}
