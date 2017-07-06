package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataSource
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.domain.repository.ConfigurationRepository
import com.jpp.moviespreview.extentions.findFirstPossibleResult

/**
 * ConfigurationRepository to retrieve the last MoviesConfiguration available from the provided sources.
 *
 * Created by jpp on 6/23/17.
 */
class ConfigurationDataRepository(private val dataSources: List<ConfigurationDataSource>) : ConfigurationRepository {

    override fun lastKnownConfiguration(): MoviesConfiguration? {
        return dataSources.findFirstPossibleResult {
            val found = it.lastKnownConfiguration()
            if (found != null) found
            else null
        }
    }
}