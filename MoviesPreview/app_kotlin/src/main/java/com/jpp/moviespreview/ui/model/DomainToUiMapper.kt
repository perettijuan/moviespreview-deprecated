package com.jpp.moviespreview.ui.model

import com.jpp.moviespreview.domain.model.ImagesConfiguration as DomainImagesConfiguration
import com.jpp.moviespreview.domain.model.MoviesConfiguration as DomainMoviesConfiguration
import com.jpp.moviespreview.domain.model.MoviePage as ModelMoviePage
import com.jpp.moviespreview.domain.model.Movie as ModelMovie

/**
 * Maps all the domain classes to presentation classes.
 *
 * Created by jpp on 6/27/17.
 */

class DomainToUiMapper {


    fun convertMoviesConfigurationFromDomainModel(domainMoviesConfiguration: DomainMoviesConfiguration) = with(domainMoviesConfiguration) {
        MoviesConfiguration(convertImagesConfigurationFromDomainModel(imagesConfiguration))
    }

    private fun convertImagesConfigurationFromDomainModel(domainImagesConfiguration: DomainImagesConfiguration) = with(domainImagesConfiguration) {
        ImagesConfiguration(baseUrl, sizes)
    }


    fun convertMoviesFromDomainModel(modelMovies: List<ModelMovie>): List<Movie> {
        return modelMovies.map {
            movie ->
            convertMovieFromDomainModel(movie)
        }
    }

    private fun convertMovieFromDomainModel(movie: ModelMovie) = with(movie) {
        Movie(genreIds, id, title, popularity, releaseDate, posterPath, overview, voteAverage, voteCount, backdropPath)
    }
}