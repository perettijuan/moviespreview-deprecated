package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.flow.sections.ApplicationSection;

import java.io.IOException;

import retrofit2.Call;
import rx.Observable;
import rx.Subscriber;

import static com.jpp.moviespreview.core.flow.sections.ApplicationSection.SectionType.IN_THEATRE;
import static com.jpp.moviespreview.core.flow.sections.ApplicationSection.SectionType.MOST_POPULAR;

/**
 * UseCase that retrieves the top rated movies.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
/*package*/ class TopRatedMoviesUseCase extends UseCase<ApplicationSection, MoviePageDto> {

    private int mCurrentPage = 0;

    @NonNull
    @Override
    protected Observable<MoviePageDto> buildObservableUseCase(final ApplicationSection appSection) {
        return Observable.create(new Observable.OnSubscribe<MoviePageDto>() {
            @Override
            public void call(Subscriber<? super MoviePageDto> subscriber) {
                try {
                    //update page
                    mCurrentPage++;

                    Call<MoviePageDto> movieCall = getApiCallForSection(appSection);
                    MoviePageDto moviePage = movieCall.execute().body();
                    if (moviePage != null) {
                        subscriber.onNext(moviePage);
                    } else {
                        subscriber.onError(new Exception("Impossible to retrieve movies"));
                    }

                } catch (IOException e) {
                    subscriber.onError(e);
                }

            }
        });
    }


    private Call<MoviePageDto> getApiCallForSection(ApplicationSection section) {
        @ApplicationSection.SectionType int type = section.getType();
        Call<MoviePageDto> call = null;
        switch (type) {
            case MOST_POPULAR:
                call = getApiInstance().topRated(mCurrentPage, BuildConfig.API_KEY);
                break;
            case IN_THEATRE:
                call = getApiInstance().nowPlaying(mCurrentPage, BuildConfig.API_KEY);
                break;
        }
        return call;
    }
}
