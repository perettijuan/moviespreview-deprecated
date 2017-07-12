package com.jpp.moviespreview.data

import com.jpp.moviespreview.BuildConfig
import com.jpp.moviespreview.data.repository.configuration.ConfigurationDataRepository
import com.jpp.moviespreview.data.repository.movies.MoviesDataRepository
import com.jpp.moviespreview.data.server.datasource.ServerConfigurationDataSource
import com.jpp.moviespreview.data.server.datasource.ServerMoviesDataSource
import com.jpp.moviespreview.data.server.retrofit.MoviesPreviewApi
import com.jpp.moviespreview.data.repository.configuration.ConfigurationRepository
import com.jpp.moviespreview.data.repository.movies.MoviesRepository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * This file contains some sort of facade access to the data layer of the application.
 *
 * Created by jpp on 6/29/17.
 */

/**
 * Dagger module to inject the DataLayerFactory single instance in the application.
 */
@Module
class DataLayerModule {

    @Provides
    @Singleton
    fun provideFactory(): DataLayerFactory = DataLayerFactoryImpl()

}

/**
 * Defines the signature of the Factory that provides access to all the components available
 * in the data layer.
 */
interface DataLayerFactory {


    fun getConfigurationRepository(): ConfigurationRepository

    fun getMoviesRepository(): MoviesRepository

}


/**
 * Private implementation of DataLayerFactory
 */
private class DataLayerFactoryImpl : DataLayerFactory {


    companion object {
        val API by lazy { createApiInstance() }

        fun createApiInstance(): MoviesPreviewApi {
            // create Retrofit instance
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
            val retrofit = Retrofit.Builder()
                    .addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(BuildConfig.API_ENDPOINT)
                    .client(client)
                    .build()
            // create API instance
            return retrofit.create(MoviesPreviewApi::class.java)
        }
    }


    // for the moment, use only server data sources. in the future will have cache access.


    override fun getConfigurationRepository()
            = ConfigurationDataRepository(listOf(ServerConfigurationDataSource(API)))

    override fun getMoviesRepository() = MoviesDataRepository(listOf(ServerMoviesDataSource(API)))
}