package com.jpp.moviespreview.core.interactors;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that creates the instances of UseCase that are
 * injected into the presenter layer.
 * <p>
 * Created by jpperetti on 7/12/16.
 */
@Module
/*package*/ class UseCaseModule {


    /**
     * @return - the instance of UseCase that responds to the parameters.
     */
    @Provides
    UseCase<Void, RemoteConfigurationDto> remoteConfiguration() {
        return new RemoteConfigurationUseCase();
    }


    /**
     * @return - the instance of UseCase to retrieve the top rated movies.
     */
    @Provides
    UseCase<MoviesContext, MoviePageDto> topRatedMovies() {
        return new TopRatedMoviesUseCase();
    }

}
