package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.core.entity.MoviePageDto;

import java.io.IOException;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

/**
 * UseCase implementation to perform a search and retrieve the results.
 * <p>
 * Created by jpperetti on 21/12/16.
 */
/*default*/ class SearchMovieUseCase extends UseCase<String, MoviePageDto> {

    private int mCurrentPage = 0;

    @NonNull
    @Override
    protected Observable<MoviePageDto> buildObservableUseCase(final String query) {
        return Observable.create(new Observable.OnSubscribe<MoviePageDto>() {
            @Override
            public void call(Subscriber<? super MoviePageDto> subscriber) {

                try {
                    mCurrentPage++;

                    Call<MoviePageDto> call = getApiInstance().search(BuildConfig.API_KEY, query, mCurrentPage, false);
                    MoviePageDto result = call.execute().body();
                    if (result == null) {
                        subscriber.onError(new Exception("Impossible to retrieve movies"));
                    } else {
                        subscriber.onNext(result);
                    }


                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
