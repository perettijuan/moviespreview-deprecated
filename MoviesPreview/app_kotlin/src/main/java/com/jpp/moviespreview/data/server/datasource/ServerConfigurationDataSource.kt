package com.jpp.moviespreview.data.server.datasource

import com.jpp.moviespreview.BuildConfig
import com.jpp.moviespreview.data.datasource.ConfigurationDataSource
import com.jpp.moviespreview.data.model.MoviesConfiguration
import com.jpp.moviespreview.data.server.ServerDataMapper
import com.jpp.moviespreview.data.server.retrofit.MoviesPreviewApi
import com.jpp.moviespreview.data.server.retrofit.unwrapCall


/**
 * ConfigurationDataSource implementation that interacts with the server (via Retrofit) to retrieve the
 * MoviesConfiguration
 *
 * Created by jpp on 6/24/17.
 */
class ServerConfigurationDataSource(private val apiInstance: MoviesPreviewApi) : ConfigurationDataSource {

    override fun lastKnownConfiguration(): MoviesConfiguration? {
        apiInstance.getLastConfiguration(BuildConfig.API_KEY).unwrapCall {
            return ServerDataMapper().convertMoviesConfigurationFromDataModel(it)
        }
    }
}