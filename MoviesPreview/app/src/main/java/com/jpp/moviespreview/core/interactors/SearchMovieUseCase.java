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
 * This uses a different approach to handle the pagination: it retrieves all the possible
 * pages relaed to the search in a thread, and notifies the subscriber to show the results.
 * <p>
 * <p>
 * Created by jpperetti on 21/12/16.
 */
/*default*/ class SearchMovieUseCase extends UseCase<String, MoviePageDto> {


    @NonNull
    @Override
    protected Observable<MoviePageDto> buildObservableUseCase(final String query) {
        return Observable.create(new Observable.OnSubscribe<MoviePageDto>() {
            @Override
            public void call(Subscriber<? super MoviePageDto> subscriber) {

                try {
                    int currentPage = 1;

                    // get first page
                    MoviePageDto result = getPage(query, currentPage);
                    if (result == null) {
                        subscriber.onError(new Exception("Impossible to retrieve movies"));
                    } else {
                        subscriber.onNext(result);

                        // retrieve the rest of the pages
                        if (result.getTotalPages() > currentPage) {
                            // update index
                            currentPage++;
                            for (int i = currentPage; i < result.getTotalPages(); i++) {
                                MoviePageDto pageResult = getPage(query, i);
                                if (pageResult != null) {
                                    subscriber.onNext(pageResult);
                                }
                            }
                        }

                    }


                } catch (IOException e) {
                    subscriber.onError(e);
                }
            }
        });
    }


    private MoviePageDto getPage(String query, int page) throws IOException {
        Call<MoviePageDto> call = getApiInstance().search(BuildConfig.API_KEY, query, page, false);
        return call.execute().body();
    }
}
