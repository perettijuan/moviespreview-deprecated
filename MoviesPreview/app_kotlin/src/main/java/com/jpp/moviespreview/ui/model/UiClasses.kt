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