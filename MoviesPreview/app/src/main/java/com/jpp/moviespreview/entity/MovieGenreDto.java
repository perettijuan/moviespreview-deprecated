package com.jpp.moviespreview.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jpperetti on 3/12/16.
 */

public class MovieGenreDto {

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
}
