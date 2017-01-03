package com.jpp.moviespreview.core.flow;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.preview.PreviewInput;

/**
 * Resolves the flow in the application.
 * <p>
 * Created by jpperetti on 3/1/17.
 */
public interface FlowResolver {

    /**
     * Executes the step to go the main screen.
     *
     * @param context  - the MoviesContext in the application.
     * @param executor - the FlowStepExecutor that will actually execute the next step.
     */
    void goToMainScreen(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor);

    /**
     * Executes the step to the movie preview section.
     *
     * @param context  - the MoviesContext in the application.
     * @param executor - the FlowStepExecutor that will actually execute the next step.
     * @param input    - the PreviewInput to start the flow.
     */
    void goToMoviePreview(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor, @NonNull PreviewInput input);

    /**
     * Executes the step that heads the flow to the search flow.
     *
     * @param context  - the MoviesContext in the application.
     * @param executor - the FlowStepExecutor that will actually execute the next step.
     */
    void goToSearch(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor);
}
