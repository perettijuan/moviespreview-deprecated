package com.jpp.moviespreview.core.interactors;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.MovieDetailDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;

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
     * @return - the instance of UseCase to retrieve the top rated movies.
     */
    @Provides
    UseCase<ApplicationSection, MoviePageDto> topRatedMovies() {
        return new GetMoviesUseCase();
    }


    /**
     * @return - the instance of UseCase that completes the application context.
     */
    @Provides
    UseCase<MoviesContext, MoviesContext> completeContext() {
        return new CompleteContextUseCase();
    }


    /**
     * @return - the UseCase instance that can perform a search.
     */
    @Provides
    UseCase<String, MoviePageDto> search() {
        return new SearchMovieUseCase();
    }


    /**
     * @return - the UseCase instance that retrieves the details of a movie.
     */
    @Provides
    UseCase<MovieDto, MovieDetailDto> detail() {
        return new RetrieveMovieDetailUseCase();
    }

}
