package com.jpp.moviespreview.domain.interactors

import com.jpp.moviespreview.domain.model.MoviePage
import com.jpp.moviespreview.domain.repository.MoviesRepository

/**
 * Created by jpp on 7/6/17.
 */
class TopRatedMoviesUseCase(private val respository: MoviesRepository) : UseCase<Int, MoviePage> {


    override fun execute(param: Int?): MoviePage? {
        // i know that param won't be null here, otherwise crash it baby
        return respository.topRated(param!!)
    }


}