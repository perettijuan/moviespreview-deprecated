package com.jpp.moviespreview.core.flow;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.home.HomeScreen;
import com.jpp.moviespreview.preview.MoviePreviewScreen;
import com.jpp.moviespreview.preview.PreviewInput;

/**
 * Resolves the flow in the application.
 * <p>
 * Created by jpperetti on 3/12/16.
 */
public class FlowResolver {


    /**
     * Executes the step to go the main screen.
     *
     * @param context  - the MoviesContext in the application.
     * @param executor - the FlowStepExecutor that will actually execute the next step.
     */
    public void goToMainScreen(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor) {
        Intent intent = new Intent(executor.getApplicationContext(), HomeScreen.class);
        intent.putExtra(MoviesContext.EXTRA_KEY, context);
        executor.executeNextStep(intent, false);
    }


    /**
     * Executes the step to the movie preview section.
     *
     * @param context  - the MoviesContext in the application.
     * @param executor - the FlowStepExecutor that will actually execute the next step.
     * @param input    - the PreviewInput to start the flow.
     */
    public void goToMoviePreview(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor, @NonNull PreviewInput input) {
        Intent intent = new Intent(executor.getApplicationContext(), MoviePreviewScreen.class);
        intent.putExtra(MoviesContext.EXTRA_KEY, context);
        input.attachInputToIntent(intent);
        executor.executeNextStep(intent, true);
    }

}
