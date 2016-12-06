package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.entity.ImagesConfigurationDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.entity.MovieGenrePage;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.mvp.BasePresenter;
import com.jpp.moviespreview.core.mvp.BasePresenterCommand;
import com.jpp.moviespreview.preview.PreviewInput;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

/**
 * BasePresenter implementation for the home.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
/* default */ class HomePresenter extends BasePresenter<HomeView> {

    private Subscriber<Object> mSubscriber;
    private MoviePageDto mCurrentPage;

    @Override
    protected void linkView(@NonNull HomeView view) {
        super.linkView(view);
        if (mCurrentPage == null) {
            if (mSubscriber == null || mSubscriber.isUnsubscribed()) {
                retrieveFirstPage();
            }
        } else {
            getView().showMoviesPage(preparePage(mCurrentPage.getMovies(), getContext().getRemoteConfiguration().getImagesConfiguration()));
        }
    }


    /**
     * Retrieve the first page of the movie list.
     */
    private void retrieveFirstPage() {
        mSubscriber = new MoviesRetriever();
        getView().showLoading();
        Observable.combineLatest(getApiInstance().genres(BuildConfig.API_KEY),
                //TODO view how to implement pagination (maybe with Observable.concatWith ??)
                getApiInstance().topRated("1", BuildConfig.API_KEY),
                new Func2<MovieGenrePage, MoviePageDto, Object>() {
                    @Override
                    public Object call(MovieGenrePage movieGenrePage, MoviePageDto pageDto) {
                        mCurrentPage = pageDto;
                        getContext().setMovieGenres(movieGenrePage);
                        return null;
                    }
                })
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }


    private List<MovieListItem> preparePage(List<MovieDto> movies, ImagesConfigurationDto imagesConfiguration) {
        List<MovieListItem> page = new ArrayList<>();
        for (MovieDto movie : movies) {
            String posterUrl = imagesConfiguration.getPosterImageBaseUrl()
                    + imagesConfiguration.getDefaultPosterSize() + movie.getPosterPath();
            String genres = getContext().getGenresById(movie.getGenreIds());
            String popularity = getView().getApplicationContext().getString(R.string.popularity,
                    String.valueOf(movie.getPopularity()));
            MovieListItem item = new MovieListItem(movie, posterUrl, genres, popularity);
            page.add(item);
        }
        return page;
    }


    /*package*/ void onMovieItemSelected(@NonNull MovieListItem listItem) {
        PreviewInput previewInput = new PreviewInput(listItem.getModel());
        getFlowResolverInstance().goToMoviePreview(getContext(), getView(), previewInput);
    }


    /**
     * {@link Subscriber} implementation to retrieve the page.
     */
    private class MoviesRetriever extends Subscriber<Object> {


        @Override
        public void onCompleted() {
            if (isViewLinked()) {
                getView().showMoviesPage(preparePage(mCurrentPage.getMovies(), getContext().getRemoteConfiguration().getImagesConfiguration()));
            }
        }

        @Override
        public void onError(Throwable e) {
            if (isViewLinked()) {
                getView().showSnackbarError(R.string.generic_error, R.string.generic_retry, new RetryActionCommand());
            }
        }

        @Override
        public void onNext(Object object) {

        }
    }


    /**
     * {@link BasePresenterCommand} to execute the retry action.
     */
    private class RetryActionCommand implements BasePresenterCommand {

        @Override
        public void executeCommand() {
            retrieveFirstPage();
        }
    }

}
