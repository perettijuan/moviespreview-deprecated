package com.jpp.moviespreview.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class to represent a MovieDto
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public class MovieDto implements Parcelable {

    @Expose
    @SerializedName("genre_ids")
    private int[] mGenreIds;

    @Expose
    @SerializedName("id")
    private long mId;

    @Expose
    @SerializedName("title")
    private String mTitle;

    @Expose
    @SerializedName("popularity")
    private float mPopularity;

    @Expose
    @SerializedName("release_date")
    private String mReleaseDate;

    @Expose
    @SerializedName("poster_path")
    private String mPosterPath;

    @Expose
    @SerializedName("overview")
    private String mOverview;

    @Expose
    @SerializedName("vote_average")
    private float mVoteAverage;

    @Expose
    @SerializedName("vote_count")
    private long mVoteCount;


    public int[] getGenreIds() {
        return mGenreIds;
    }

    public long getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public float getPopularity() {
        return mPopularity;
    }

    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getPosterPath() {
        return mPosterPath;
    }

    public String getOverview() {
        return mOverview;
    }

    public float getVoteAverage() {
        return mVoteAverage;
    }

    public long getVoteCount() {
        return mVoteCount;
    }


    //-- parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeIntArray(this.mGenreIds);
        dest.writeLong(this.mId);
        dest.writeString(this.mTitle);
        dest.writeFloat(this.mPopularity);
        dest.writeString(this.mReleaseDate);
        dest.writeString(this.mPosterPath);
        dest.writeString(this.mOverview);
        dest.writeFloat(this.mVoteAverage);
        dest.writeLong(this.mVoteCount);
    }

    public MovieDto() {
    }

    private MovieDto(Parcel in) {
        this.mGenreIds = in.createIntArray();
        this.mId = in.readLong();
        this.mTitle = in.readString();
        this.mPopularity = in.readFloat();
        this.mReleaseDate = in.readString();
        this.mPosterPath = in.readString();
        this.mOverview = in.readString();
        this.mVoteAverage = in.readFloat();
        this.mVoteCount = in.readLong();
    }

    public static final Creator<MovieDto> CREATOR = new Creator<MovieDto>() {
        public MovieDto createFromParcel(Parcel source) {
            return new MovieDto(source);
        }

        public MovieDto[] newArray(int size) {
            return new MovieDto[size];
        }
    };
}
