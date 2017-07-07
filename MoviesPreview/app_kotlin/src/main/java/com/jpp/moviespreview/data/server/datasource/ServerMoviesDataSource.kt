package com.jpp.moviespreview.data.server.datasource

import com.jpp.moviespreview.BuildConfig
import com.jpp.moviespreview.data.repository.datasource.MoviesDataSource

import com.jpp.moviespreview.data.server.ServerDataMapper
import com.jpp.moviespreview.data.server.retrofit.MoviesPreviewApi
import com.jpp.moviespreview.data.server.retrofit.unwrapCall
import com.jpp.moviespreview.domain.model.MoviePage

/**
 * MoviesDataSource implementation to retrieve results from the server
 *
 * Created by jpp on 7/6/17.
 */
class ServerMoviesDataSource(private val apiInstance: MoviesPreviewApi) : MoviesDataSource {


    override fun topRated(page: Int): MoviePage? {
        apiInstance.getTopRatedMovies(page, BuildConfig.API_KEY).unwrapCall {
            return ServerDataMapper().convertMoviePageFromDataModel(it)
        }
    }

    override fun inTheatres(page: Int): MoviePage? {
        apiInstance.getInTheatres(page, BuildConfig.API_KEY).unwrapCall {
            return ServerDataMapper().convertMoviePageFromDataModel(it)
        }
    }


}