package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.core.entity.MovieDetailDto;
import com.jpp.moviespreview.core.entity.MovieDto;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import rx.Observable;
import rx.Subscriber;

/**
 * UseCase implementation to retreieve a MovieDetailDto
 * <p>
 * Created by jpperetti on 21/12/16.
 */
/*default*/ class RetrieveMovieDetailUseCase extends UseCase<MovieDto, MovieDetailDto> {


    @NonNull
    @Override
    protected Observable<MovieDetailDto> buildObservableUseCase(final MovieDto movieDto) {
        return Observable.create(new Observable.OnSubscribe<MovieDetailDto>() {
            @Override
            public void call(Subscriber<? super MovieDetailDto> subscriber) {
                try {
                    long movieId = movieDto.getId();
                    Call<MovieDetailDto> call = getApiInstance().getMovieDetail(movieId, BuildConfig.API_KEY);
                    Response<MovieDetailDto> response = call.execute();
                    MovieDetailDto detail = response.body();
                    if (detail == null) {
                        subscriber.onError(new Exception("Unable to retrieve movie detail"));
                    } else {
                        subscriber.onNext(detail);
                        subscriber.onCompleted();
                    }
                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }
}
