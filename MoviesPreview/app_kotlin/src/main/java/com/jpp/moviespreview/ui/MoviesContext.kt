package com.jpp.moviespreview.ui

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by jpp on 6/19/17.
 */
class MoviesContext constructor() : Parcelable {


    /**
     * Private constructor for Parcelable implementation.
     *
     * Note that ` : this()` is how we declare a secondary constructor in Kotlin.
     */
    private constructor(source: Parcel?) : this() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    override fun writeToParcel(dest: Parcel?, flags: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun describeContents(): Int {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


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