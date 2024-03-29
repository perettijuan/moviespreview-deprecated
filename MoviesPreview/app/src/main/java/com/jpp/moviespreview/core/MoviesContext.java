package com.jpp.moviespreview.core;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;

import com.jpp.moviespreview.core.entity.MovieGenreDto;
import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;

import java.util.ArrayList;
import java.util.List;

/**
 * Utility class that holds all the information related to the context of the application
 * <p>
 * Created by jpperetti on 3/12/16.
 */
public class MoviesContext implements Parcelable {

    public static final String EXTRA_KEY = "movies_key";

    private RemoteConfigurationDto mRemoteConfiguration;
    private MovieGenrePage mMovieGenres;
    private List<ApplicationSection> mSections;

    private SparseArray<MovieGenreDto> movieGenresMap;


    @Nullable
    public RemoteConfigurationDto getRemoteConfiguration() {
        return mRemoteConfiguration;
    }

    public void setRemoteConfiguration(@NonNull RemoteConfigurationDto mRemoteConfiguration) {
        this.mRemoteConfiguration = mRemoteConfiguration;
    }

    @Nullable
    public MovieGenrePage getGenresPage() {
        return mMovieGenres;
    }

    public void setMovieGenres(@NonNull MovieGenrePage mMovieGenres) {
        this.mMovieGenres = mMovieGenres;
    }

    @Nullable
    public List<ApplicationSection> getSections() {
        return mSections;
    }

    public void setSections(@NonNull List<ApplicationSection> sections) {
        this.mSections = sections;
    }


    /**
     * Creates a String containing the names of all the Genres identified
     * in the array passed as parameter
     */
    public String getGenresById(int[] ids) {

        if (movieGenresMap == null) {
            createMovieGenresMap();
        }

        StringBuilder sb = new StringBuilder();
        for (Integer id : ids) {
            MovieGenreDto genre = movieGenresMap.get(id);
            if(genre != null) {
                sb.append(" ").append(genre.getName());
            }
        }

        return sb.toString();
    }


    /**
     * Create and populate the sparse array once, in order to avoid overhead
     * of processing.
     */
    private void createMovieGenresMap() {
        movieGenresMap = new SparseArray<>();
        for (MovieGenreDto genre : mMovieGenres.getMovieGenres()) {
            movieGenresMap.append(genre.getId(), genre);
        }
    }

    //////// Parcelable //////////


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.mRemoteConfiguration, 0);
        dest.writeParcelable(this.mMovieGenres, 0);
        dest.writeTypedList(mSections);
    }

    public MoviesContext() {
    }

    private MoviesContext(Parcel in) {
        this.mRemoteConfiguration = in.readParcelable(RemoteConfigurationDto.class.getClassLoader());
        this.mMovieGenres = in.readParcelable(MovieGenrePage.class.getClassLoader());
        mSections = new ArrayList<>();
        in.readTypedList(mSections, ApplicationSection.CREATOR);
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
