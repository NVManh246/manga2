package com.powerranger.comic.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ComicDetail implements Parcelable {
    @SerializedName("_id")
    private String mId;
    @SerializedName("title")
    private String mTitle;
    @SerializedName("category")
    private List<String> mKinds;
    @SerializedName("author")
    private List<String> mAuthors;
    @SerializedName("status")
    private String mStatus;
    @SerializedName("update")
    private String mUpdate;
    @SerializedName("cover")
    private String mCover;
    @SerializedName("description")
    private String mDescription;

    public ComicDetail() {
    }


    protected ComicDetail(Parcel in) {
        mId = in.readString();
        mTitle = in.readString();
        mKinds = in.createStringArrayList();
        mAuthors = in.createStringArrayList();
        mStatus = in.readString();
        mUpdate = in.readString();
        mCover = in.readString();
        mDescription = in.readString();
    }

    public static final Creator<ComicDetail> CREATOR = new Creator<ComicDetail>() {
        @Override
        public ComicDetail createFromParcel(Parcel in) {
            return new ComicDetail(in);
        }

        @Override
        public ComicDetail[] newArray(int size) {
            return new ComicDetail[size];
        }
    };

    public String getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public List<String> getKinds() {
        return mKinds;
    }

    public List<String> getAuthors() {
        return mAuthors;
    }

    public String getStatus() {
        return mStatus;
    }

    public String getUpdate() {
        return mUpdate;
    }

    public String getCover() {
        return mCover;
    }

    public String getDescription() {
        return mDescription;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(mId);
        parcel.writeString(mTitle);
        parcel.writeStringList(mKinds);
        parcel.writeStringList(mAuthors);
        parcel.writeString(mStatus);
        parcel.writeString(mUpdate);
        parcel.writeString(mCover);
        parcel.writeString(mDescription);
    }
}
