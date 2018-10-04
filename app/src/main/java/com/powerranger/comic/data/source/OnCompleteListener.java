package com.powerranger.comic.data.source;

public interface OnCompleteListener<T> {
    void onSuccess(T t);
    void onFailure(Throwable throwable);
}
