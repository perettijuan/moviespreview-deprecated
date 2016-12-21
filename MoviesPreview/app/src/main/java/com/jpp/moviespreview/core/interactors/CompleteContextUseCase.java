package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func2;

/**
 * UseCase implementation to complete the MoviesContext
 * <p>
 * Created by jpperetti on 21/12/16.
 */
/*default*/ class CompleteContextUseCase extends UseCase<MoviesContext, MoviesContext> {
    @NonNull
    @Override
    protected Observable<MoviesContext> buildObservableUseCase(final MoviesContext moviesContext) {
        Observable<RemoteConfigurationDto> configObservable = buildRemoteConfigurationRetriever();
        Observable<MovieGenrePage> genresObservable = buildMovieGenresRetriever();
        return Observable.zip(configObservable, genresObservable, new Func2<RemoteConfigurationDto, MovieGenrePage, MoviesContext>() {
            @Override
            public MoviesContext call(RemoteConfigurationDto remoteConfigurationDto, MovieGenrePage movieGenrePage) {
                moviesContext.setSections(ApplicationSection.newInstance());
                moviesContext.setRemoteConfiguration(remoteConfigurationDto);
                moviesContext.setMovieGenres(movieGenrePage);
                return moviesContext;
            }
        });
    }


    /**
     * Builds an Observable that retrieves the RemoteConfigurationDto from the server.
     *
     * @return - the Observable that will retrieve the remote configuration from the server.
     */
    private Observable<RemoteConfigurationDto> buildRemoteConfigurationRetriever() {
        return Observable.create(new Observable.OnSubscribe<RemoteConfigurationDto>() {
            @Override
            public void call(Subscriber<? super RemoteConfigurationDto> subscriber) {
                try {
                    Call<RemoteConfigurationDto> call = getApiInstance().configurations(BuildConfig.API_KEY);
                    Response<RemoteConfigurationDto> response = call.execute();
                    RemoteConfigurationDto remoteConfiguration = response.body();
                    if (remoteConfiguration == null) {
                        subscriber.onError(new Exception("Unable to retrieve configuration from server"));
                    } else {
                        subscriber.onNext(remoteConfiguration);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    /**
     * Builds an Observable that retrieves the MovieGenres from the server.
     *
     * @return - the Observable that will retrieve the MovieGenres.
     */
    private Observable<MovieGenrePage> buildMovieGenresRetriever() {
        return Observable.create(new Observable.OnSubscribe<MovieGenrePage>() {
            @Override
            public void call(Subscriber<? super MovieGenrePage> subscriber) {
                try {
                    Call<MovieGenrePage> genresCall = getApiInstance().genres(BuildConfig.API_KEY);
                    MovieGenrePage genres = genresCall.execute().body();
                    if (genres == null) {
                        subscriber.onError(new Exception("Impossible to retrieve movie genres"));
                    } else {
                        subscriber.onNext(genres);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }

}
