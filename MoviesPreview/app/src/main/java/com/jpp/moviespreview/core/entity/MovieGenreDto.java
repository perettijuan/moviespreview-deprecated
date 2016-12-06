package com.jpp.moviespreview.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jpperetti on 3/12/16.
 */

public class MovieGenreDto implements Parcelable {

    @Expose
    @SerializedName("id")
    private int mId;

    @Expose
    @SerializedName("name")
    private String mName;


    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }


    //-- parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.mId);
        dest.writeString(this.mName);
    }

    public MovieGenreDto() {
    }

    private MovieGenreDto(Parcel in) {
        this.mId = in.readInt();
        this.mName = in.readString();
    }

    public static final Creator<MovieGenreDto> CREATOR = new Creator<MovieGenreDto>() {
        public MovieGenreDto createFromParcel(Parcel source) {
            return new MovieGenreDto(source);
        }

        public MovieGenreDto[] newArray(int size) {
            return new MovieGenreDto[size];
        }
    };
}
