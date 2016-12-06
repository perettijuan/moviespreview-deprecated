package com.jpp.moviespreview.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Represents a remote configuration.
 * <p>
 * Created by jpperetti on 12/2/16.
 */
public class RemoteConfigurationDto implements Parcelable {

    @Expose
    @SerializedName("images")
    private ImagesConfigurationDto mImagesConfiguration;


    public ImagesConfigurationDto getImagesConfiguration() {
        return mImagesConfiguration;
    }


    //////// PARCELABLE IMPLEMENTATION ///////////

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mImagesConfiguration, 0);
    }

    public RemoteConfigurationDto() {
    }

    private RemoteConfigurationDto(Parcel in) {
        this.mImagesConfiguration = in.readParcelable(ImagesConfigurationDto.class.getClassLoader());
    }

    public static final Creator<RemoteConfigurationDto> CREATOR = new Creator<RemoteConfigurationDto>() {
        public RemoteConfigurationDto createFromParcel(Parcel source) {
            return new RemoteConfigurationDto(source);
        }

        public RemoteConfigurationDto[] newArray(int size) {
            return new RemoteConfigurationDto[size];
        }
    };
}
