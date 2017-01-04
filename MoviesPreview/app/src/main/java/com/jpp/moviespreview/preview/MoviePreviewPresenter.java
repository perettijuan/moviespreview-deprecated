package com.jpp.moviespreview.preview;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.entity.ImagesConfigurationDto;
import com.jpp.moviespreview.core.entity.MovieDetailDto;
import com.jpp.moviespreview.core.entity.MovieDto;
import com.jpp.moviespreview.core.entity.MovieGenreDto;
import com.jpp.moviespreview.core.entity.ProductionCompanyDto;
import com.jpp.moviespreview.core.interactors.UseCase;
import com.jpp.moviespreview.core.interactors.UseCaseObserver;
import com.jpp.moviespreview.core.mvp.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * BasePresenter for the movies preview section.
 * <p>
 * Created by jpperetti on 6/12/16.
 */
public class MoviePreviewPresenter extends BasePresenter<MoviePreviewView> implements UseCaseObserver<MovieDetailDto> {

    @Inject
    UseCase<MovieDto, MovieDetailDto> mUseCase;

    private MovieDto mSelectedMovie;

    @Override
    protected void init(@Nullable Bundle extraData) {
        super.init(extraData);
        if (extraData != null) {
            mSelectedMovie = PreviewInput.dettachFromExtra(extraData);
        }
        UseCase.getDependencyInyectionComponent().inject(this);
    }


    @Override
    protected void linkView(@NonNull MoviePreviewView view) {
        super.linkView(view);

        // show initial info while retrieving the details
        ImagesConfigurationDto imagesConfiguration = getContext().getRemoteConfiguration().getImagesConfiguration();
        String posterUrl = imagesConfiguration.getPosterImageBaseUrl() + imagesConfiguration.getDefaultPosterSize() + mSelectedMovie.getPosterPath();
        String backdropUrl = imagesConfiguration.getPosterImageBaseUrl() + imagesConfiguration.getDefaultPosterSize() + mSelectedMovie.getBackdropPath();
        MoviePreviewItem item = new MoviePreviewItem(mSelectedMovie, posterUrl, backdropUrl);
        view.showMovie(item);

        mUseCase.execute(mSelectedMovie, this);
    }


    //-- process use case response

    @Override
    public void onDataProcessed(MovieDetailDto data) {
        if (isViewLinked()) {
            // prepare the data to show
            Context context = getView().getApplicationContext();
            List<MoviePreviewItemDetail> details = new ArrayList<>();

            // overview
            details.add(new MoviePreviewItemDetail(context.getString(R.string.detail_overview), mSelectedMovie.getOverview()));

            // genres
            StringBuilder sb = new StringBuilder();
            for (MovieGenreDto genre : data.getGenres()) {
                sb.append(genre.getName());
            }
            String genreTitle = context.getResources().getQuantityString(R.plurals.detail_genre, data.getGenres().size());
            details.add(new MoviePreviewItemDetail(genreTitle, sb.toString()));

            // release date
            details.add(new MoviePreviewItemDetail(context.getString(R.string.detail_release_date), data.getResleaseDate()));

            // budget
            details.add(new MoviePreviewItemDetail(context.getString(R.string.detail_budget), String.valueOf(data.getBudget())));

            // revenue
            details.add(new MoviePreviewItemDetail(context.getString(R.string.detail_revenue), String.valueOf(data.getRevenue())));

            // producers
            sb = new StringBuilder();
            for (ProductionCompanyDto company : data.getProducers()) {
                sb.append(company.getName());
            }
            details.add(new MoviePreviewItemDetail(context.getString(R.string.detail_produced), sb.toString()));

            getView().showDetails(details);
        }
    }

    @Override
    public void onError(Throwable error) {
        //TODO implement
    }

    @Override
    public void onProcessDone() {
        // nothing for the moment.
    }
}
