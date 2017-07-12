package com.jpp.moviespreview.ui.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Contains all the data classes that represents the model of the UI layer.
 *
 * Created by jpp on 6/27/17.
 */

/**
 * Represents the configuration of the images tha the data layer can provide.
 */
data class ImagesConfiguration(val baseUrl: String, val sizes: List<String>) : Parcelable {

    constructor(source: Parcel) : this(source.readString(), source.createStringArrayList())

    fun defaultPosterSize() = sizes[sizes.size - 1]

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeString(baseUrl)
        dest?.writeStringList(sizes)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<ImagesConfiguration> = object : Parcelable.Creator<ImagesConfiguration> {

            override fun createFromParcel(source: Parcel): ImagesConfiguration {
                return ImagesConfiguration(source)
            }

            override fun newArray(size: Int): Array<ImagesConfiguration?> {
                return arrayOfNulls(size)
            }
        }
    }

}

/**
 * Represents the general configuration of the movies
 */
data class MoviesConfiguration(val imagesConfiguration: ImagesConfiguration) : Parcelable {


    constructor(source: Parcel) : this(source.readParcelable<ImagesConfiguration>(ImagesConfiguration::class.java.classLoader))

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(imagesConfiguration, flags)
    }

    override fun describeContents() = 0

    companion object {
        @JvmField final val CREATOR: Parcelable.Creator<MoviesConfiguration> = object : Parcelable.Creator<MoviesConfiguration> {
            override fun createFromParcel(source: Parcel): MoviesConfiguration {
                return MoviesConfiguration(source)
            }

            override fun newArray(size: Int): Array<MoviesConfiguration?> {
                return arrayOfNulls(size)
            }

        }
    }

}

// TODO check two things: 1 - are we using all properties? / 2 - genres should be a List of Genre class
data class Movie(var genreIds: List<Int>, var id: Long, var title: String, var popularity: Float,
                 var releaseDate: String, var posterPath: String, var overview: String,
                 var voteAverage: Float, var voteCount: Long, var backdropPath: String) : Parcelable {

    companion object {
        @JvmField val CREATOR: Parcelable.Creator<Movie> = object : Parcelable.Creator<Movie> {
            override fun createFromParcel(source: Parcel): Movie = Movie(source)
            override fun newArray(size: Int): Array<Movie?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
            ArrayList<Int>().apply { source.readList(this, Int::class.java.classLoader) },
            source.readLong(),
            source.readString(),
            source.readFloat(),
            source.readString(),
            source.readString(),
            source.readString(),
            source.readFloat(),
            source.readLong(),
            source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeList(genreIds)
        dest.writeLong(id)
        dest.writeString(title)
        dest.writeFloat(popularity)
        dest.writeString(releaseDate)
        dest.writeString(posterPath)
        dest.writeString(overview)
        dest.writeFloat(voteAverage)
        dest.writeLong(voteCount)
        dest.writeString(backdropPath)
    }
}
