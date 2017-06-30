package com.jpp.moviespreview.data.repository

import com.jpp.moviespreview.data.repository.datasource.ConfigurationDataSource
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.domain.repository.ConfigurationRepository
import com.jpp.moviespreview.extentions.firstResult

/**
 * ConfigurationRepository to retrieve the last MoviesConfiguration available from the provided sources.
 *
 * Created by jpp on 6/23/17.
 */
class ConfigurationDataRepository(private val dataSources: List<ConfigurationDataSource>) : ConfigurationRepository {

    override fun lastKnownConfiguration(): MoviesConfiguration? = requestToSources {
        val found = it.lastKnownConfiguration()
        if (found != null) found
        else null
    }


    /**
     * Generified function to iterate over all the dataSources and retrieve the first MoviesConfiguration in it.
     * <T : Any> ---> generified
     * f: (ConfigurationDataSource) -> T? ---> a function f that receives a ConfigurationDataSource as parameter and returns a T (nullable)
     * dataSources.firstResult { f(it) } ---> function body: executes the f over each ConfigurationDataSource in the list and returns the first
     * result that is not null.
     */
    //TODO this is a good candidate to create your own extension method
    private fun <T : Any> requestToSources(f: (ConfigurationDataSource) -> T?): T? =
            try {
                dataSources.firstResult { f(it) }
            } catch (e: NoSuchElementException) {
                null
            }

}