package com.jpp.moviespreview.domain.mapper

import com.jpp.moviespreview.domain.model.ImagesConfiguration
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.data.model.ImagesConfiguration as DataImagesConfiguration
import com.jpp.moviespreview.data.model.MoviesConfiguration as DataMoviesConfiguration

/**
 * Maps configuration classes from the data layer to the domain layer
 *
 * Created by jpp on 7/10/17.
 */
class ConfigurationDataMapper {

    /**
     * Converts the MoviesConfiguration from data to model class.
     */
    fun convertMoviesConfigurationFromDataModel(dataMoviesConfiguration: DataMoviesConfiguration) = with(dataMoviesConfiguration) {
        MoviesConfiguration(convertImagesConfigurationFromDataModel(dataMoviesConfiguration.imagesConfiguration))
    }

    private fun convertImagesConfigurationFromDataModel(imagesConfiguration: DataImagesConfiguration) = with(imagesConfiguration) {
        ImagesConfiguration(baseUrl, sizes)
    }

}