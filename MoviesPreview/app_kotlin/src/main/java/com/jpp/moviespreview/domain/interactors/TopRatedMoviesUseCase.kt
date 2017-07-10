package com.jpp.moviespreview.domain.interactors

import com.jpp.moviespreview.data.repository.movies.MoviesRepository
import com.jpp.moviespreview.domain.mapper.MoviesDataMapper
import com.jpp.moviespreview.domain.model.MoviePage

/**
 * Created by jpp on 7/6/17.
 */
class TopRatedMoviesUseCase(private val respository: MoviesRepository) : UseCase<Int, MoviePage> {


    override fun execute(param: Int?): MoviePage? {
        // i know that param won't be null here, otherwise crash it baby
        val dataPage = respository.topRated(param!!)
        if (dataPage == null) {
            return null
        } else {
            return MoviesDataMapper().convertMoviePageFromDataModel(dataPage)
        }
    }


}