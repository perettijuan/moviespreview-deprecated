package com.jpp.moviespreview.splash;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.interactors.UseCaseObserver;
import com.jpp.moviespreview.core.mvp.BasePresenter;
import com.jpp.moviespreview.core.mvp.BasePresenterCommand;
import com.jpp.moviespreview.core.entity.RemoteConfigurationDto;
import com.jpp.moviespreview.splash.interactors.RemoteConfigurationUseCase;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter for the splash screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
/* default */ class SplashPresenter extends BasePresenter<SplashView> {

    private UseCaseObserver<RemoteConfigurationDto> mSubscriber;


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
        mSubscriber = new RemoteConfigurationRetriever();
        new RemoteConfigurationUseCase().execute(null, mSubscriber);
    }


    /**
     * {@link Subscriber} implementation to handle the {@link RemoteConfigurationDto} retrieval process.
     */
    private class RemoteConfigurationRetriever implements UseCaseObserver<RemoteConfigurationDto> {

        @Override
        public void onDataProcessed(RemoteConfigurationDto data) {
            getContext().setRemoteConfiguration(data);
        }

        @Override
        public void onError(Throwable e) {
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
