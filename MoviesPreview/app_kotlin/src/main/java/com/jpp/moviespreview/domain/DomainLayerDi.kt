package com.jpp.moviespreview.domain

import com.jpp.moviespreview.data.DataLayerFactory
import com.jpp.moviespreview.domain.interactors.RetrieveConfigurationUseCase
import com.jpp.moviespreview.domain.interactors.UseCase
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * This file contains some sort of facade access to the domain layer of the application.
 *
 * Created by jpp on 6/29/17.
 */


/**
 * Dagger module to inject the UseCaseFactory single instance in the application.
 */
@Module
class DomainLayerModule {


    @Provides
    @Singleton
    fun provideFactory(dataLaterFactory: DataLayerFactory): UseCaseFactory = UseCaseFactoryImpl(dataLaterFactory)

}

/**
 * Interface that defines the signature of access and creation of object to the domain layer.
 */
interface UseCaseFactory {

    companion object {
        val USE_CASE_FACTORY_SYSTEM = "USE_CASE_FACTORY_SYSTEM"
    }


    /**
     * Retrieves the UseCase that can retrieve the latest MoviesConfiguration instance.
     */
    fun moviesConfiguration(): UseCase<Any, MoviesConfiguration>


}


/**
 * Private implementation of UseCaseFactory.
 */
private class UseCaseFactoryImpl(private var mDataLaterFactory: DataLayerFactory) : UseCaseFactory {


    override fun moviesConfiguration() = RetrieveConfigurationUseCase(mDataLaterFactory.getConfigurationRepository())

}