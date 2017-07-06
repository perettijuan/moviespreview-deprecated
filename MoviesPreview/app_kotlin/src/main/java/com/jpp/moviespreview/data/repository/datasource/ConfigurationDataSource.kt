package com.jpp.moviespreview.data.repository.datasource

import com.jpp.moviespreview.domain.model.MoviesConfiguration

/**
 * Datasource definition for MoviesConfiguration
 * Created by jpp on 6/23/17.
 */
interface ConfigurationDataSource {


    /**
     * Retrieves the last known configuration available
     */
    fun lastKnownConfiguration(): MoviesConfiguration?
}