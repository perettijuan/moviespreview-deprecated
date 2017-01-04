package com.jpp.moviespreview.core.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents the detail of a movie.
 * <p>
 * Created by jpperetti on 21/12/16.
 */
public class MovieDetailDto {

    @Expose
    @SerializedName("budget")
    private long mBudget;

    @Expose
    @SerializedName("revenue")
    private long mRevenue;

    @Expose
    @SerializedName("genres")
    private List<MovieGenreDto> mGenres;

    @Expose
    @SerializedName("homepage")
    private String mHomePage;


    @Expose
    @SerializedName("production_companies")
    private List<ProductionCompanyDto> mProducers;

    @Expose
    @SerializedName("release_date")
    private String mReleaseDate;


    public long getBudget() {
        return mBudget;
    }

    public long getRevenue() {
        return mRevenue;
    }

    public List<MovieGenreDto> getGenres() {
        return mGenres;
    }

    public String getHomePage() {
        return mHomePage;
    }

    public List<ProductionCompanyDto> getProducers() {
        return mProducers;
    }

    public String getResleaseDate() {
        return mReleaseDate;
    }

}
