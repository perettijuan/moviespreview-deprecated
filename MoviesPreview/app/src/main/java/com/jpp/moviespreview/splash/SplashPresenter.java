package com.jpp.moviespreview.splash;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.BuildConfig;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenter;
import com.jpp.moviespreview.core.mvp.BasePresenterCommand;
import com.jpp.moviespreview.entity.RemoteConfigurationDto;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Presenter for the splash screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
/* default */ class SplashPresenter extends BasePresenter<SplashView> {

    private Subscriber<RemoteConfigurationDto> mSubscriber;


    @Override
    protected void linkView(@NonNull SplashView view) {
        super.linkView(view);
        if (getContext().getRemoteConfiguration() != null) {
            getFlowResolverInstance().goToMainScreen(getContext(), getView());
        } else if (mSubscriber == null || mSubscriber.isUnsubscribed()) {
            retrieveRemoteConfig();
        }
    }


    private void retrieveRemoteConfig() {
        mSubscriber = new RemoteConfigurationRetriever();
        getApiInstance().configurations(BuildConfig.API_KEY)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }


    /**
     * {@link Subscriber} implementation to handle the {@link RemoteConfigurationDto} retrieval process.
     */
    private class RemoteConfigurationRetriever extends Subscriber<RemoteConfigurationDto> {

        @Override
        public void onCompleted() {
            if (isViewLinked()) {
                getFlowResolverInstance().goToMainScreen(getContext(), getView());
            }
        }

        @Override
        public void onError(Throwable e) {
            if (isViewLinked()) {
                getView().showSnackbarError(R.string.generic_error, R.string.generic_retry, new RetryActionCommand());
            }
        }

        @Override
        public void onNext(RemoteConfigurationDto remoteConfigurationDto) {
            getContext().setRemoteConfiguration(remoteConfigurationDto);
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
