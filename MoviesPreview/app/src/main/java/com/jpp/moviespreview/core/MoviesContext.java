package com.jpp.moviespreview.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jpp.moviespreview.entity.RemoteConfigurationDto;

/**
 * Utility class that holds all the information related to the context of the application
 * <p>
 * Created by jpperetti on 3/12/16.
 */
public class MoviesContext implements Parcelable {

    public static final String EXTRA_KEY = "movies_key";

    private RemoteConfigurationDto mRemoteConfiguration;


    @Nullable
    public RemoteConfigurationDto getRemoteConfiguration() {
        return mRemoteConfiguration;
    }

    public void setRemoteConfiguration(@NonNull RemoteConfigurationDto mRemoteConfiguration) {
        this.mRemoteConfiguration = mRemoteConfiguration;
    }


    //////// Parcelable //////////


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mRemoteConfiguration, 0);
    }

    public MoviesContext() {
    }

    private MoviesContext(Parcel in) {
        this.mRemoteConfiguration = in.readParcelable(RemoteConfigurationDto.class.getClassLoader());
    }

    public static final Creator<MoviesContext> CREATOR = new Creator<MoviesContext>() {
        public MoviesContext createFromParcel(Parcel source) {
            return new MoviesContext(source);
        }

        public MoviesContext[] newArray(int size) {
            return new MoviesContext[size];
        }
    };
}
