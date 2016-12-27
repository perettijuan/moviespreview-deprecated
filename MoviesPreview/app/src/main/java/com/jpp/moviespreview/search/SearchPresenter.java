package com.jpp.moviespreview.search;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.entity.ImagesConfigurationDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.entity.MoviePageDto;
import com.jpp.moviespreview.core.interactors.UseCase;
import com.jpp.moviespreview.core.interactors.UseCaseObserver;
import com.jpp.moviespreview.core.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * BasePresenter for the search screen (MVP implementation).
 * <p>
 * implements {@link UseCaseObserver} in order to try an alternative approach to the other
 * presenters ({@link com.jpp.moviespreview.home.HomePresenter} for instance) in which the observers
 * are inner classes.
 * <p>
 * Created by jpperetti on 19/12/16.
 */

public class SearchPresenter extends BasePresenter<SearchView> implements UseCaseObserver<MoviePageDto> {

    @Inject
    UseCase<String, MoviePageDto> mUseCase;

    private String mCurrentMovieTitle;
    private List<SearchMovieListItem> mMoviesListItems;


    /**
     * Performs the search of a given movie.
     *
     * @param movieTitle - the title of the movie to search.
     */
    void searchMovie(@NonNull String movieTitle) {
        getView().showLoading();

        if (!movieTitle.equals(mCurrentMovieTitle)) {
            // inject in order to create new instance each time that the query changes.
            UseCase.getDependencyInyectionComponent().inject(this);
            mCurrentMovieTitle = movieTitle;
            mMoviesListItems = new ArrayList<>();
        }

        mUseCase.execute(mCurrentMovieTitle, this);
    }


    @Override
    protected void linkView(@NonNull SearchView view) {
        super.linkView(view);
        if (mMoviesListItems != null && !mMoviesListItems.isEmpty()) {
            view.showSearchResult(mMoviesListItems);
        }
    }

    // FROM UseCaseObserver
    @Override
    public void onDataProcessed(MoviePageDto data) {
        if (isViewLinked()) {
            List<SearchMovieListItem> preparedPage = preparePage(data.getMovies());
            mMoviesListItems.addAll(preparedPage);
            getView().showSearchResult(mMoviesListItems);
        }
    }

    @Override
    public void onError(Throwable error) {
        if (isViewLinked()) {
            getView().showSnackbarError(R.string.generic_error, 0, null);
        }
    }

    @Override
    public void onProcessDone() {

    }


    /**
     * Prepares the page of SearchMovieListItem to be shown.
     */
    private List<SearchMovieListItem> preparePage(List<MovieDto> moviesInPage) {
        List<SearchMovieListItem> page = new ArrayList<>();
        ImagesConfigurationDto imagesConfiguration = getContext().getRemoteConfiguration().getImagesConfiguration();
        for (MovieDto movie : moviesInPage) {
            String posterUrl = imagesConfiguration.getPosterImageBaseUrl()
                    + imagesConfiguration.getDefaultPosterSize() + movie.getPosterPath();
            SearchMovieListItem item = new SearchMovieListItem(movie, posterUrl);
            page.add(item);
        }
        return page;
    }

}
