package com.jpp.moviespreview.core.mvp;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;

import com.jpp.moviespreview.core.flow.FlowStepExecutor;

/**
 * Generic interface to present information to the user. Part of the MPV pattern.
 * <p>
 * Created by jpperetti on 8/2/16.
 */
public interface PresentingView extends FlowStepExecutor {

    /**
     * Shows an error in the Android's snackbar view.
     *
     * @param errorMessage   - the error message to show
     * @param recoverMessage - the recoverMessage message to show (if any)
     * @param command        - the action to execute when user press the action in the snackbar.
     */
    void showSnackbarError(@NonNull String errorMessage, @Nullable String recoverMessage, @Nullable BasePresenterCommand command);

    /**
     * Shows an error in the Android's snackbar view.
     *
     * @param errorMessage   - the error message to show
     * @param recoverMessage - the recoverMessage message to show (if any)
     * @param command        - the action to execute when user press the action in the snackbar.
     */
    void showSnackbarError(@StringRes int errorMessage, @StringRes int recoverMessage, @Nullable BasePresenterCommand command);

    /**
     * Shows an error in the Android's snackbar view.
     *
     * @param errorMessage - the error message to show
     */
    void showSnackbarError(@StringRes int errorMessage);

    /**
     * Shows an error in the Android's snackbar view with a warning configuration.
     *
     * @param errorMessage - the error message to show
     */
    void showSnackbarWarning(@StringRes int errorMessage);
}
