package com.jpp.moviespreview.core.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpperetti on 3/12/16.
 */
public class MovieGenrePage implements Parcelable {

    @Expose
    @SerializedName("genres")
    private List<MovieGenreDto> mGenres;


    public List<MovieGenreDto> getMovieGenres() {
        return mGenres;
    }


    //-- parcelable implementation

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mGenres);
    }

    public MovieGenrePage() {
    }

    private MovieGenrePage(Parcel in) {
        mGenres = new ArrayList<>();
        in.readTypedList(mGenres, MovieGenreDto.CREATOR);
    }

    public static final Creator<MovieGenrePage> CREATOR = new Creator<MovieGenrePage>() {
        public MovieGenrePage createFromParcel(Parcel source) {
            return new MovieGenrePage(source);
        }

        public MovieGenrePage[] newArray(int size) {
            return new MovieGenrePage[size];
        }
    };
}
