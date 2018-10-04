package com.powerranger.comic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Chapter implements Parcelable {
    @SerializedName("_id")
    private String mId;
    @SerializedName("title")
    private String mName;
    private boolean mIsReading;

    public Chapter(String id, String name) {
        mId = id;
        mName = name;
    }

    protected Chapter(Parcel in) {
        mId = in.readString();
        mName = in.readString();
    }

    public static final Creator<Chapter> CREATOR = new Creator<Chapter>() {
        @Override
        public Chapter createFromParcel(Parcel in) {
            return new Chapter(in);
        }

        @Override
        public Chapter[] newArray(int size) {
            return new Chapter[size];
        }
    };

    public String getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public void setReading(boolean isReading) {
        mIsReading = isReading;
    }

    public boolean isReading() {
        return mIsReading;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mName);
    }
}
