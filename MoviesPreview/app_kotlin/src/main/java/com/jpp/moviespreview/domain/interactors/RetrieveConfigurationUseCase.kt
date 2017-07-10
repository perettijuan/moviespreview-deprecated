package com.jpp.moviespreview.domain.interactors

import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.data.repository.configuration.ConfigurationRepository
import com.jpp.moviespreview.domain.mapper.ConfigurationDataMapper

/**
 * UseCase that retrieves the last known MoviesConfiguration
 *
 * Created by jpp on 6/23/17.
 */
class RetrieveConfigurationUseCase(private val repository: ConfigurationRepository)
    : UseCase<Any, MoviesConfiguration> {

    override fun execute(param: Any?): MoviesConfiguration? {
        val dataConfig = repository.lastKnownConfiguration()
        if (dataConfig == null) {
            return null
        } else {
            return ConfigurationDataMapper().convertMoviesConfigurationFromDataModel(dataConfig)
        }
    }

}