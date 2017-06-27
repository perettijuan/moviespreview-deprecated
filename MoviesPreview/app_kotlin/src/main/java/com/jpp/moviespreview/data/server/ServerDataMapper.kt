package com.jpp.moviespreview.data.server

import com.jpp.moviespreview.domain.model.ImagesConfiguration as ModelImagesConfiguration
import com.jpp.moviespreview.domain.model.MoviesConfiguration as ModelMoviesConfiguration

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
}


