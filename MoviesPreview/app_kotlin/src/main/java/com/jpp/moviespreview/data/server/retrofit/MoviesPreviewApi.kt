package com.jpp.moviespreview.data.server.retrofit

import com.jpp.moviespreview.data.server.MoviesConfiguration
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by jpp on 6/27/17.
 */
interface MoviesPreviewApi {

    @GET("configuration")
    fun getLastConfiguration(@Query("api_key") api_key: String): Call<MoviesConfiguration>

}