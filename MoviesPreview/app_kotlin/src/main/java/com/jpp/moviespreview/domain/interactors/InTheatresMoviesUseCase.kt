package com.jpp.moviespreview.domain.interactors

import com.jpp.moviespreview.domain.model.MoviePage
import com.jpp.moviespreview.data.repository.movies.MoviesRepository
import com.jpp.moviespreview.domain.mapper.MoviesDataMapper

/**
 * Created by jpp on 7/6/17.
 */
class InTheatresMoviesUseCase(private val respository: MoviesRepository) : UseCase<Int, MoviePage> {


    override fun execute(param: Int?): MoviePage? {
        // i know that param won't be null here, otherwise crash it baby
        val dataPage = respository.inTheatres(param!!)
        if (dataPage == null) {
            return null
        } else {
            return MoviesDataMapper().convertMoviePageFromDataModel(dataPage)
        }
    }


}