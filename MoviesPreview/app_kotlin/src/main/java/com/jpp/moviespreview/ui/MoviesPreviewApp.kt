package com.jpp.moviespreview.ui

import android.app.Application
import com.facebook.drawee.backends.pipeline.Fresco
import com.jpp.moviespreview.data.DataLayerFactory
import com.jpp.moviespreview.data.DataLayerModule
import com.jpp.moviespreview.domain.DomainLayerModule
import com.jpp.moviespreview.domain.UseCaseFactory
import dagger.Component
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Application implementation for the entire application. This class is injected with the
 * graph of use case factory and provides access to the UseCases in the application via
 * UseCaseFactory.
 *
 * Created by jpp on 6/29/17.
 */
class MoviesPreviewApp : Application() {

    /*
     * injected via Dagger - lateinit allows to declare a constant (var) without
     * initializing in the declaration.
     */
    @Inject
    lateinit var mUseCaseFactory: UseCaseFactory

    /*
     * Dependency Injection Dagger Component - delegated property
     * lazy() is a standard Kotlin Delegate - a lambda expression that returns an instance of T
     * (in this case DaggerMoviesPreviewAppComponent) and remembers the result, subsequent calls
     * simply return the remembered result.
     */
    val component: MoviesPreviewAppComponent by lazy {
        DaggerMoviesPreviewAppComponent.builder()
                .dataLayerModule(DataLayerModule())
                .domainLayerModule(DomainLayerModule())
                .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
        Fresco.initialize(this)
    }

    /**
     * Provide access to the UseCaseFactory instance created for the application
     * via the system service framework.
     */
    override fun getSystemService(name: String?): Any {
        if (UseCaseFactory.USE_CASE_FACTORY_SYSTEM == name) {
            return mUseCaseFactory
        }
        return super.getSystemService(name)
    }
}

/**
 * Dagger component to inject elements.
 */
@Singleton
@Component(modules = arrayOf(DomainLayerModule::class, DataLayerModule::class))
interface MoviesPreviewAppComponent {

    /**
     * Provides a DataLayerFactory instance from the DataLayerModule to be injected
     * in the injected as parameter of DomainLayerModule#provideFactory().
     */
    fun dataLayerFactory(): DataLayerFactory

    /**
     * Injects the MoviesPreviewApp components.
     */
    fun inject(app: MoviesPreviewApp)

}

