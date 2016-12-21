package com.jpp.moviespreview.splash;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;
import com.jpp.moviespreview.core.interactors.UseCase;
import com.jpp.moviespreview.core.interactors.UseCaseObserver;
import com.jpp.moviespreview.core.mvp.BasePresenter;
import com.jpp.moviespreview.core.mvp.BasePresenterCommand;

import javax.inject.Inject;

import rx.Subscriber;

/**
 * Presenter for the splash screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
public class SplashPresenter extends BasePresenter<SplashView> {

    private UseCaseObserver<MoviesContext> mSubscriber;

    @Inject
    UseCase<MoviesContext, MoviesContext> useCase;

    @Override
    protected void linkView(@NonNull SplashView view) {
        super.linkView(view);
        if (getContext().getRemoteConfiguration() != null) {
            getFlowResolverInstance().goToMainScreen(getContext(), getView());
        } else if (mSubscriber == null) {
            retrieveRemoteConfig();
        }
    }


    private void retrieveRemoteConfig() {
        mSubscriber = new CompleteContextCallback();
        UseCase.getDependencyInyectionComponent().inject(this);
        useCase.execute(getContext(), mSubscriber);
    }


    private class CompleteContextCallback implements UseCaseObserver<MoviesContext> {

        @Override
        public void onDataProcessed(MoviesContext data) {
            //DO NOTHING
        }

        @Override
        public void onError(Throwable error) {
            if (isViewLinked()) {
                getView().showSnackbarError(R.string.generic_error, R.string.generic_retry, new RetryActionCommand());
            }
        }

        @Override
        public void onProcessDone() {
            if (isViewLinked()) {
                getFlowResolverInstance().goToMainScreen(getContext(), getView());
            }
        }
    }


    /**
     * {@link BasePresenterCommand} to execute the retry action.
     */
    private class RetryActionCommand implements BasePresenterCommand {

        @Override
        public void executeCommand() {
            retrieveRemoteConfig();
        }
    }
}
