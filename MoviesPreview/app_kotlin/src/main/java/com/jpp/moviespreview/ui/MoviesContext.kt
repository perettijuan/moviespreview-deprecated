package com.jpp.moviespreview.ui

import android.os.Parcel
import android.os.Parcelable
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

    /**
     * Private constructor for Parcelable implementation.
     *
     * Note that ` : this()` is how we declare a secondary constructor in Kotlin.
     */
    private constructor(source: Parcel?) : this() {
        moviesConfiguration = source?.readParcelable(MoviesConfiguration::class.java.classLoader)
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeParcelable(moviesConfiguration, flags)
    }

    override fun describeContents() = 0


    companion object {

        val EXTRA_KEY = "movies_context_key"

        /**
         * Way of creating the CREATOR field requested by Parcelable contract.
         *
         * the annotation @JvmField indicates to the compiler that does not generate getter and
         * setter for this property.
         */
        @JvmField final val CREATOR: Parcelable.Creator<MoviesContext> = object : Parcelable.Creator<MoviesContext> {
            override fun createFromParcel(source: Parcel?): MoviesContext {
                return MoviesContext(source)
            }

            override fun newArray(size: Int): Array<MoviesContext?> {
                return arrayOfNulls(size)
            }

        }
    }
}