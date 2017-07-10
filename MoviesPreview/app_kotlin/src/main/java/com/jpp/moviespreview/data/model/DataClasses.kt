package com.jpp.moviespreview.data.model

/**
 * Contains the data classes that defines the model that the data layer cominicates to it's
 * clients.
 *
 * Created by jpp on 7/10/17.
 */

/**
 * Represents the configuration of the images tha the data layer can provide.
 */
data class ImagesConfiguration(val baseUrl: String, val sizes: List<String>)

/**
 * Represents the general configuration of the movies
 */
data class MoviesConfiguration(val imagesConfiguration: ImagesConfiguration)