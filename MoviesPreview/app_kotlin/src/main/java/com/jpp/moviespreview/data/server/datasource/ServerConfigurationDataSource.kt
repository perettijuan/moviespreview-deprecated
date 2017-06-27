package com.jpp.moviespreview.data.server.datasource

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataSource
import com.jpp.moviespreview.data.server.ServerDataMapper
import com.jpp.moviespreview.data.server.retrofit.MoviesPreviewApi
import com.jpp.moviespreview.data.server.retrofit.unwrapCall
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import javax.inject.Inject

/**
 * ConfigurationDataSource implementation that interacts with the server (via Retrofit) to retrieve the
 * MoviesConfiguration
 *
 * Created by jpp on 6/24/17.
 */
class ServerConfigurationDataSource @Inject constructor(private val apiInstance: MoviesPreviewApi,
                                                        private val apiKey: String) : ConfigurationDataSource {

    override fun lastKnownConfiguration(): MoviesConfiguration? {
        apiInstance.getLastConfiguration(apiKey).unwrapCall {
            return ServerDataMapper().convertMoviesConfigurationFromDataModel(it)
        }
    }
}