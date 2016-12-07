package com.jpp.moviespreview.core.interactors;

/**
 * An abstraction that is used with {@link UseCase}s to be notified about events
 *
 * @param <T> - the type of data to be notified about.
 */
public interface UseCaseObserver<T> {


    void onDataProcessed(T data);


    void onError(Throwable error);


    void onProcessDone();


}
