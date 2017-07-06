package com.jpp.moviespreview.data.server.retrofit

import com.jpp.moviespreview.data.server.MoviePage
import com.jpp.moviespreview.data.server.MoviesConfiguration
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * API instance for Retrofit
 *
 * Created by jpp on 6/27/17.
 */
interface MoviesPreviewApi {

    @GET("configuration")
    fun getLastConfiguration(@Query("api_key") api_key: String): Call<MoviesConfiguration>

    @GET("discover/movie?sort_by=popularity.desc")
    fun getTopRatedMovies(@Query("page") page: Int, @Query("api_key") api_key: String) : Call<MoviePage>

    @GET("movie/now_playing")
    fun getInTheatres(@Query("page") page: Int, @Query("api_key") api_key: String) : Call<MoviePage>
}