package com.jpp.moviespreview.data.server.datasource

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataStore
import com.jpp.moviespreview.domain.model.MoviesConfiguration

/**
 * Created by jpp on 6/24/17.
 */
class ServerConfigurationDataStore : ConfigurationDataStore {
    override fun lastKnownConfiguration(): MoviesConfiguration? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        //Use Retrofit here
    }
}