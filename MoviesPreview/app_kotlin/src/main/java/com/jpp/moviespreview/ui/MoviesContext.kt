package com.jpp.moviespreview.ui

import android.os.Parcel
import android.os.Parcelable
import com.jpp.moviespreview.ui.model.Movie
import com.jpp.moviespreview.ui.model.MoviesConfiguration

/**
 * Application context implementation: it contains all the entities that are needed at UI layer
 * to provide the app's functionality.
 *
 * An instance of this class is created by the first BasePresenter instantiated by the application
 * and then it's passed via parcelable in the Intents that creates the Activities.
 *
 * Created by jpp on 6/19/17.
 */
class MoviesContext constructor() : Parcelable {
    var moviesConfiguration: MoviesConfiguration? = null
    var movies: MutableList<Movie> = mutableListOf()


    constructor(source: Parcel) : this() {
        moviesConfiguration = source.readParcelable(MoviesConfiguration::class.java.classLoader)
        movies = mutableListOf()
        source.readTypedList<Movie>(movies, Movie.CREATOR)
    }

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeParcelable(moviesConfiguration, flags)
        dest.writeTypedList<Movie>(movies)
    }


    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MoviesContext> = object : Parcelable.Creator<MoviesContext> {
            override fun createFromParcel(source: Parcel): MoviesContext = MoviesContext(source)
            override fun newArray(size: Int): Array<MoviesContext?> = arrayOfNulls(size)
        }
    }
}