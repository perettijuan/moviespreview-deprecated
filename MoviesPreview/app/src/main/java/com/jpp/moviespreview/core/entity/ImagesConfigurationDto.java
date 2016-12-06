package com.jpp.moviespreview.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents the configuration of the images.
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public class ImagesConfigurationDto implements Parcelable {

    @Expose
    @SerializedName("base_url")
    private String mBaseUrl;

    @Expose
    @SerializedName("poster_sizes")
    String[] mPosterSizes;

    public String getPosterImageBaseUrl() {
        return mBaseUrl;
    }

    public String getDefaultPosterSize() {
        return mPosterSizes[mPosterSizes.length - 1];
    }


    //////// PARCELABLE IMPLEMENTATION ///////////

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mBaseUrl);
        dest.writeStringArray(this.mPosterSizes);
    }

    public ImagesConfigurationDto() {

    }

    private ImagesConfigurationDto(Parcel in) {
        this.mBaseUrl = in.readString();
        this.mPosterSizes = in.createStringArray();
    }

    public static final Creator<ImagesConfigurationDto> CREATOR = new Creator<ImagesConfigurationDto>() {
        public ImagesConfigurationDto createFromParcel(Parcel source) {
            return new ImagesConfigurationDto(source);
        }

        public ImagesConfigurationDto[] newArray(int size) {
            return new ImagesConfigurationDto[size];
        }
    };
}
