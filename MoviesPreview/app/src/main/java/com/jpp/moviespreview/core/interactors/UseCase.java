package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jpp.moviespreview.core.DaggerDiComponent;
import com.jpp.moviespreview.core.DiComponent;
import com.jpp.moviespreview.core.UtilityWrapper;
import com.jpp.moviespreview.core.flow.FlowModule;
import com.jpp.moviespreview.core.network.MoviesPreviewApi;
import com.jpp.moviespreview.core.network.RestModule;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;


/**
 * Base class for UseCases that are executed to transform an input Param into a Response.
 *
 * @param <Param>    - the parameter to be transformed.
 * @param <Response> - the response after the execution.
 */
public abstract class UseCase<Param, Response> {

    // The RxJava Subscription
    private Subscription mSubscription = Subscriptions.empty();


    private UtilityWrapper utilityWrapper;


    public UseCase() {
        // inject the members every time the presenter is linked
        utilityWrapper = new UtilityWrapper();
        DiComponent component = DaggerDiComponent.builder()
                .restModule(new RestModule())
                .flowModule(new FlowModule())
                .build();
        component.inject(utilityWrapper);
    }


    /**
     * Method that performs the UseCase.
     *
     * @param param      - the parameter to be transformed by the UseCase.
     * @param subscriber - the observer that will listen for events from the UseCase.
     */
    public void execute(@Nullable Param param, @NonNull final UseCaseObserver<Response> subscriber) {

        mSubscription = buildObservableUseCase(param)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<Response>() {
                    @Override
                    public void onCompleted() {
                        if (!mSubscription.isUnsubscribed()) {
                            mSubscription.unsubscribe();
                        }
                        subscriber.onProcessDone();
                    }

                    @Override
                    public void onError(Throwable e) {
                        subscriber.onError(e);
                    }

                    @Override
                    public void onNext(Response response) {
                        subscriber.onDataProcessed(response);
                    }
                });
    }

    /**
     * @return - the instance of MoviesPreviewApi to be used by the presenter
     */
    protected MoviesPreviewApi getApiInstance() {
        return utilityWrapper.getApiInstance();
    }

    /**
     * Method in which the actual work is performed. Each sub-class of UseCase will
     * perform the actual work described in this method.
     *
     * @param param - the Param to transform.
     * @return - the Observable instance to subscribe for events.
     */
    @NonNull
    protected abstract Observable<Response> buildObservableUseCase(Param param);


}
