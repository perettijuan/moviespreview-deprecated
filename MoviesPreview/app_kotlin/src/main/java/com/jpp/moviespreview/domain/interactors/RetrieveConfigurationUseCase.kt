package com.jpp.moviespreview.domain.interactors

import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.domain.repository.ConfigurationRepository

/**
 * UseCase that retrieves the last known MoviesConfiguration
 *
 * Created by jpp on 6/23/17.
 */
class RetrieveConfigurationUseCase(private val repository: ConfigurationRepository)
    : UseCase<Any, MoviesConfiguration> {

    override fun execute(param: Any?): MoviesConfiguration? {
        return repository.lastKnownConfiguration()
    }

}