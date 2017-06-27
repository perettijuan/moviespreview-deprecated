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