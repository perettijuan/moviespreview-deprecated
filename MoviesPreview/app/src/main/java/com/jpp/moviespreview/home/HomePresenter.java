package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.ImagesConfigurationDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.interactors.UseCase;
import com.jpp.moviespreview.core.interactors.UseCaseObserver;
import com.jpp.moviespreview.core.mvp.BasePresenter;
import com.jpp.moviespreview.core.mvp.BasePresenterCommand;
import com.jpp.moviespreview.preview.PreviewInput;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * BasePresenter implementation for the home.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
public class HomePresenter extends BasePresenter<HomeView> {

    private List<MovieListItem> mMovieListItems;
    @Inject
    UseCase<MoviesContext, MoviePageDto> mUseCase;

    @Override
    protected void linkView(@NonNull HomeView view) {
        super.linkView(view);
        if (mUseCase == null) {
            retrieveFirstPage();
        } else {
            getView().showMoviesPage(mMovieListItems);
        }
    }


    /**
     * Retrieve the first page of the movie list.
     */
    private void retrieveFirstPage() {
        getView().showLoading();
        if (mUseCase == null) {
            UseCase.getDependencyInyectionComponent().inject(this);
        }

        if (mMovieListItems == null) {
            mMovieListItems = new ArrayList<>();
        }

        mUseCase.execute(getContext(), new GetMoviesUseCaseObserver());
    }


    /**
     * Prepares a list of MovieDto to be shown by setting the MovieListItem that
     * holds the information.
     */
    private List<MovieListItem> preparePage(List<MovieDto> movies) {
        ImagesConfigurationDto imagesConfiguration = getContext().getRemoteConfiguration().getImagesConfiguration();
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


    /**
     * Called when a MovieListItem is selected
     *
     * @param listItem - the selected MovieListItem
     */
    /*package*/ void onMovieItemSelected(@NonNull MovieListItem listItem) {
        PreviewInput previewInput = new PreviewInput(listItem.getModel());
        getFlowResolverInstance().goToMoviePreview(getContext(), getView(), previewInput);
    }


    /**
     * Load the observable that will "listen" for scrolling events.
     */
    private void loadScrollListener(Observable<RecyclerViewScrollEvent> observable) {
        observable.subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<RecyclerViewScrollEvent>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(RecyclerViewScrollEvent recyclerViewScrollEvent) {
                        if (isViewLinked() && getView().shouldLoadNewPage(5)) {
                            mUseCase.execute(getContext(), new GetMoviesUseCaseObserver());
                            unsubscribe();
                        }
                    }
                });
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


    private class GetMoviesUseCaseObserver implements UseCaseObserver<MoviePageDto> {

        @Override
        public void onDataProcessed(MoviePageDto data) {
            if (isViewLinked()) {
                List<MovieListItem> preparedItems = preparePage(data.getMovies());
                mMovieListItems.addAll(preparedItems);
                getView().showMoviesPage(mMovieListItems);
                loadScrollListener(getView().getScrollingObservable());
            }
        }

        @Override
        public void onError(Throwable error) {
            if (isViewLinked()) {
                getView().showSnackbarError(R.string.generic_error, R.string.generic_retry, new RetryActionCommand());
            }
        }

        @Override
        public void onProcessDone() {

        }
    }

}
