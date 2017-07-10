package com.jpp.moviespreview.domain.mapper


import com.jpp.moviespreview.domain.model.MoviePage
import com.jpp.moviespreview.domain.model.Movie
import com.jpp.moviespreview.data.model.MoviePage as DataMoviePage
import com.jpp.moviespreview.data.model.Movie as DataMovie

/**
 * Converts movies classes from data model to domain model
 *
 * Created by jpp on 7/10/17.
 */
class MoviesDataMapper {

    /**
     * Converts a MoviePage to a model movie page.
     */
    fun convertMoviePageFromDataModel(moviePage: DataMoviePage) = with(moviePage) {
        MoviePage(convertMoviesFromDataModel(movies), page, totalResults, totalPages)
    }

    /**
     * Converts a List of Movies (data) to a list of ModelMovie (domain)
     */
    private fun convertMoviesFromDataModel(moviesList: List<DataMovie>): List<Movie> {
        // map iterates over the list and returns a list of the result of applying the transformation to each item
        return moviesList.map {
            movie ->
            convertMovieFromDataModel(movie)
        }
    }

    /**
     * Converts a single Movie from the data module to the domain module.
     */
    private fun convertMovieFromDataModel(movie: DataMovie) = with(movie) {
        Movie(genreIds, id, title, popularity, releaseDate, posterPath, overview, voteAverage, voteCount, backdropPath)
    }
}