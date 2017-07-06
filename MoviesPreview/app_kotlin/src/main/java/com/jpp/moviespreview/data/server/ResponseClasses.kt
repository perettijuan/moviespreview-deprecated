package com.jpp.moviespreview.data.server

/**
 * Data classes for the server responses.
 * Since Gson is used to parse the responses, the variable names for each attribute must respect the
 * same name og Json attribs.
 *
 * Created by jpp on 6/24/17.
 */
data class ImagesConfiguration(var base_url: String, var poster_sizes: List<String>)

data class MoviesConfiguration(var images: ImagesConfiguration)

data class Movie(var genre_ids: List<Int>, var id: Long, var title: String, var popularity: Float,
                 var release_date: String, var poster_path: String, var overview: String,
                 var vote_average: Float, var vote_count: Long, var backdrop_path: String)

data class MoviePage(var results: List<Movie>, var page: Int, var total_results: Long,
                     var total_pages: Long)