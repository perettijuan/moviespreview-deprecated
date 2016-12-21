package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.entity.MoviePageDto;

import rx.Observable;

/**
 * UseCase implementation to perform a search and retrieve the results.
 * <p>
 * Created by jpperetti on 21/12/16.
 */
/*default*/ class SearchMovieUseCase extends UseCase<String, MoviePageDto> {
    @NonNull
    @Override
    protected Observable<MoviePageDto> buildObservableUseCase(final String query) {



        return null;
    }
}
