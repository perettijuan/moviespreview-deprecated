package com.jpp.moviespreview.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Model class to represent a MovieDto
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public class MovieDto {

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

}
