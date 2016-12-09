package com.jpp.moviespreview.home;

import android.support.annotation.NonNull;
import android.support.annotation.StringRes;

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
     * Clears the currently shown page.
     */
    void clearPage();

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


    /**
     * Shows the list of possible selection in the home screen
     *
     * @param items - the items to show.
     */
    void showHomeMenu(@NonNull List<HomeMenuListItem> items);


    /**
     * Sets the title of the screen.
     *
     * @param title - the resource identifier for the title.
     */
    void setTitle(@StringRes int title);
}
