package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataStore
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.domain.repository.ConfigurationRepository
import com.jpp.moviespreview.extentions.firstResult
import javax.inject.Inject

/**
 * Created by jpp on 6/23/17.
 */
class ConfigurationDataRepository @Inject constructor(private val dataStores: List<ConfigurationDataStore>) : ConfigurationRepository {

    override fun lastKnownConfiguration(): MoviesConfiguration? = requestToSources {
        val found = it.lastKnownConfiguration()
        if (found != null) found
        else null
    }


    /**
     * Generified function to iterate over all the dataStores and retrieve the first MoviesConfiguration in it.
     * <T : Any> ---> generified
     * f: (ConfigurationDataStore) -> T? ---> a function f that receives a ConfigurationDataStore as parameter and returns a T (nullable)
     * dataStores.firstResult { f(it) } ---> function body: executes the f over each ConfigurationDataStore in the list and returns the first
     * result that is not null.
     */
    //TODO this is a good candidate to create your own extension method
    private fun <T : Any> requestToSources(f: (ConfigurationDataStore) -> T?): T = dataStores.firstResult { f(it) }

}