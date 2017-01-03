package com.jpp.moviespreview.core.flow;

import android.content.Intent;
import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.home.HomeScreen;
import com.jpp.moviespreview.preview.MoviePreviewScreen;
import com.jpp.moviespreview.preview.PreviewInput;
import com.jpp.moviespreview.search.SearchScreen;

/**
 * Resolves the flow in the application.
 * <p>
 * Created by jpperetti on 3/12/16.
 */
/*package*/ class MoviesFlowResolver implements FlowResolver {


    @Override
    public void goToMainScreen(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor) {
        Intent intent = new Intent(executor.getApplicationContext(), HomeScreen.class);
        intent.putExtra(MoviesContext.EXTRA_KEY, context);
        executor.executeNextStep(intent, false);
    }


    @Override
    public void goToMoviePreview(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor, @NonNull PreviewInput input) {
        Intent intent = new Intent(executor.getApplicationContext(), MoviePreviewScreen.class);
        intent.putExtra(MoviesContext.EXTRA_KEY, context);
        input.attachInputToIntent(intent);
        executor.executeNextStep(intent, true);
    }


    @Override
    public void goToSearch(@NonNull MoviesContext context, @NonNull FlowStepExecutor executor) {
        Intent intent = new Intent(executor.getApplicationContext(), SearchScreen.class);
        intent.putExtra(MoviesContext.EXTRA_KEY, context);
        executor.executeNextStep(intent, true);
    }

}
