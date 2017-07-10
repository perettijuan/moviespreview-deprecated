package com.jpp.moviespreview.data.repository.configuration

import com.jpp.moviespreview.data.model.MoviesConfiguration


/**
 * Repository definition for MoviesConfigurations
 */
interface ConfigurationRepository {

    /**
     * Retrieves the last known MoviesConfiguration from the available source.
     */
    fun lastKnownConfiguration(): MoviesConfiguration?

}