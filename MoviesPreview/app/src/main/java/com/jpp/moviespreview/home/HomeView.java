package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jpp.moviespreview.core.mvp.PresentingView;

import java.util.List;

import rx.Observable;

/**
 * PresentingView implementation for the home screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
public interface HomeView extends PresentingView {


    void performInitialAnimations();

    /**
     * Shows the loading UI.
     */
    void showLoading();

    /**
     * Shows the current Movies page.
     */
    void showMoviesPage(@NonNull List<MovieListItem> page);


    /**
     * @return - an Observable that can be used to detect scrolling on the screen.
     */
    @NonNull
    Observable<RecyclerViewScrollEvent> getScrollingObservable();


    /**
     * Calculates if the new page should be retrieved based
     * on the offset passed as parameter.
     *
     * @param offset - the offset to use in the calculation.
     * @return - true if new page should be loaded, false any other case.
     */
    boolean shouldLoadNewPage(int offset);
}
