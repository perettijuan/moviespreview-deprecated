package com.jpp.moviespreview.core.mvp;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.MoviesContext;

/**
 * Interface to implements delegates for the presenters
 * Created by jpperetti on 6/12/16.
 */
public interface PresenterDelegate<T extends PresentingView> {

    void linkView(@NonNull T view, @NonNull MoviesContext context);

    void updateView(@NonNull T view, @NonNull MoviesContext context);
}