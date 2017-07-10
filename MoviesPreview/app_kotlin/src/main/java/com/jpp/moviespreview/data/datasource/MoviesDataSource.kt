package com.jpp.moviespreview.data.datasource

import com.jpp.moviespreview.domain.model.MoviePage


/**
 * Datasource definition to retrieve a MoviePage
 * Created by jpp on 7/6/17.
 */
interface MoviesDataSource {

    /**
     * Retrieves a MoviePage that contains the top rated movies
     */
    fun topRated(page: Int): MoviePage?

    /**
     * Retrieves a MoviePage that contains the movies in theatres at this time.
     */
    fun inTheatres(page: Int): MoviePage?

}