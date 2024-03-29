package com.jpp.moviespreview.data.repository.movies

import com.jpp.moviespreview.data.datasource.MoviesDataSource
import com.jpp.moviespreview.data.model.MoviePage
import com.jpp.moviespreview.extentions.findFirstPossibleResult

/**
 * MoviesRepository implementation for the data layer
 *
 * Created by jpp on 7/6/17.
 */
class MoviesDataRepository(private val dataSources: List<MoviesDataSource>) : MoviesRepository {


    override fun topRated(page: Int): MoviePage? {
        return dataSources.findFirstPossibleResult {
            val found = it.topRated(page)
            if (found != null) found
            else null
        }
    }

    override fun inTheatres(page: Int): MoviePage? {
        return dataSources.findFirstPossibleResult {
            val found = it.inTheatres(page)
            if (found != null) found
            else null
        }
    }

}