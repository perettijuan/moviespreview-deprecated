package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.MoviesDataSource
import com.jpp.moviespreview.domain.model.MoviePage
import com.jpp.moviespreview.domain.repository.MoviesRepository
import com.jpp.moviespreview.extentions.findFirstPossibleResult

/**
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