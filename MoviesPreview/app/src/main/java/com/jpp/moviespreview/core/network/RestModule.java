package com.jpp.moviespreview.core.network;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Dagger Module to provide injection instances for network access.
 * <p>
 * Created by jpperetti on 12/2/16.
 */
@Module
public class RestModule {


    @Provides
    @Named("endpointApi")
    String endpointApi() {
        return BuildConfig.API_ENDPOINT;
    }


    /**
     * Provider method for the Retrofit single instance
     */
    @Provides
    @Singleton
    Retrofit retrofitProvider(@Named("endpointApi") String baseUrl) {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(baseUrl)
                .build();
    }


    /**
     * Provider method for the MoviesPreviewApi
     */
    @Provides
    @Singleton
    MoviesPreviewApi injectMoviesApi(@NonNull Retrofit retrofit) {
        return retrofit.create(MoviesPreviewApi.class);
    }

}
