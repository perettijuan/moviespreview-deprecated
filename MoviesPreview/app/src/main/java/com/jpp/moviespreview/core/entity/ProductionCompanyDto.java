package com.jpp.moviespreview.core.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a company that produces movies.
 * <p>
 * Created by jpperetti on 21/12/16.
 */
public class ProductionCompanyDto {

    @Expose
    @SerializedName("name")
    private String mName;


    public String getName() {
        return mName;
    }

}
