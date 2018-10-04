package com.powerranger.comic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Comic implements Parcelable {
    @SerializedName("_id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("cover")
    private String mCover;

    public String getCover() {
        return mCover;
    }

    public Comic() {
    }

    public Comic(String id, String title, String cover) {
        mId = id;
        mTitle = title;
        mCover = cover;
    }

    protected Comic(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mCover = in.readString();
    }

    public static final Creator<Comic> CREATOR = new Creator<Comic>() {
        @Override
        public Comic createFromParcel(Parcel in) {
            return new Comic(in);
        }

        @Override
        public Comic[] newArray(int size) {
            return new Comic[size];
        }
    };

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeString(mCover);
    }
}
