package com.jpp.moviespreview.core.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Model class that represents a page of results.
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public class MoviePageDto {

    @Expose
    @SerializedName("results")
    private List<MovieDto> mResults;

    @Expose
    @SerializedName("page")
    private int mPage;

    @Expose
    @SerializedName("total_results")
    private long mTotalResults;

    @Expose
    @SerializedName("total_pages")
    private long mTotalPages;

    public List<MovieDto> getMovies() {
        return mResults;
    }


    public int getPage() {
        return mPage;
    }

    public long getTotalResults() {
        return mTotalResults;
    }

    public long getTotalPages() {
        return mTotalPages;
    }
}
