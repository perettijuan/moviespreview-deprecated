package com.jpp.moviespreview.data.server

import com.jpp.moviespreview.domain.model.ImagesConfiguration as ModelImagesConfiguration
import com.jpp.moviespreview.domain.model.MoviesConfiguration as ModelMoviesConfiguration
import com.jpp.moviespreview.domain.model.MoviePage as ModelMoviePage
import com.jpp.moviespreview.domain.model.Movie as ModelMovie

/**
 * Mapper between the Data layer and the domain layer.
 *
 * Created by jpp on 6/24/17.
 */
class ServerDataMapper {


    /**
     * Converts the MoviesConfiguration from data to model class.
     */
    fun convertMoviesConfigurationFromDataModel(dataMoviesConfiguration: MoviesConfiguration) = with(dataMoviesConfiguration) {
        ModelMoviesConfiguration(convertImagesConfigurationFromDataModel(dataMoviesConfiguration.images))
    }

    private fun convertImagesConfigurationFromDataModel(imagesConfiguration: ImagesConfiguration) = with(imagesConfiguration) {
        ModelImagesConfiguration(base_url, poster_sizes)
    }


    /**
     * Converts a MoviePage to a model movie page.
     */
    fun convertMoviePageFromDataModel(moviePage: MoviePage) = with(moviePage) {
        ModelMoviePage(convertMoviesFromDataModel(results), page, total_results, total_pages)
    }

    /**
     * Converts a List of Movies (data) to a list of ModelMovie (domain)
     */
    private fun convertMoviesFromDataModel(moviesList: List<Movie>): List<ModelMovie> {
        // map iterates over the list and returns a list of the result of applying the transformation to each item
        return moviesList.map {
            movie ->
            convertMovieFromDataModel(movie)
        }
    }

    /**
     * Converts a single Movie from the data module to the domain module.
     */
    private fun convertMovieFromDataModel(movie: Movie) = with(movie) {
        ModelMovie(genre_ids, id, title, popularity, release_date, poster_path, overview, vote_average, vote_count, backdrop_path)
    }

}


