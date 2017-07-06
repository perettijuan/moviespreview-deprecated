package com.jpp.moviespreview.domain.model

/**
 * Represents the configuration of the images tha the data layer can provide.
 */
data class ImagesConfiguration(val baseUrl: String, val sizes: List<String>)

/**
 * Represents the general configuration of the movies
 */
data class MoviesConfiguration(val imagesConfiguration: ImagesConfiguration)


data class Movie(var genreIds: List<Int>, var id: Long, var title: String, var popularity: Float,
                 var releaseDate: String, var posterPath: String, var overview: String,
                 var voteAverage: Float, var voteCount: Long, var backdropPath: String)

data class MoviePage(var movies: List<Movie>, var page: Int, var totalResults: Long,
                     var totalPages: Long)