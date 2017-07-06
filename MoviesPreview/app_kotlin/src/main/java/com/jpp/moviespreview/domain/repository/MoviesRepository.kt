package com.jpp.moviespreview.domain.repository

import com.jpp.moviespreview.domain.model.MoviePage

/**
 * Created by jpp on 7/6/17.
 */
interface MoviesRepository {

    /**
     * Retrieves a MoviePage that contains the top rated movies
     */
    fun topRated(page: Int): MoviePage?

    /**
     * Retrieves a MoviePage that contains the movies in theatres at this time.
     */
    fun inTheatres(page: Int): MoviePage?
}