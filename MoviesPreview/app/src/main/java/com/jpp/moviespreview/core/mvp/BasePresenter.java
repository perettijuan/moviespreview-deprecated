package com.jpp.moviespreview.core.mvp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.jpp.moviespreview.core.DaggerDiComponent;
import com.jpp.moviespreview.core.DiComponent;
import com.jpp.moviespreview.core.MoviesContext;
import com.jpp.moviespreview.core.UtilityWrapper;
import com.jpp.moviespreview.core.flow.FlowModule;
import com.jpp.moviespreview.core.flow.FlowResolver;
import com.jpp.moviespreview.core.network.MoviesPreviewApi;
import com.jpp.moviespreview.core.network.RestModule;

import java.lang.ref.WeakReference;

/**
 * Presenter base part of the MVP pattern.
 * <p>
 * Created by jpperetti on 8/2/16.
 */
public class BasePresenter<T extends PresentingView> {

    private WeakReference<T> viewRef;

    private UtilityWrapper utilityWrapper;

    private MoviesContext mContext;


    /**
     * Links the {@link PresentingView} to the Presenter.
     *
     * @param view - the presenting view instance
     */
    protected void linkView(@NonNull final T view) {
        this.viewRef = new WeakReference<>(view);

        // inject the members every time the presenter is linked
        utilityWrapper = new UtilityWrapper();
        DiComponent component = DaggerDiComponent.builder()
                .restModule(new RestModule())
                .flowModule(new FlowModule())
                .build();
        component.inject(utilityWrapper);
    }


    /**
     * Called on the init of the flow. Implementations should initialize
     * data here.
     *
     * @param extraData - Bundle that contains any extra data
     */
    protected void init(@Nullable Bundle extraData) {
        // extract any extra data
        if (extraData != null && extraData.containsKey(MoviesContext.EXTRA_KEY)) {
            mContext = extraData.getParcelable(MoviesContext.EXTRA_KEY);
        } else {
            mContext = new MoviesContext();
        }
    }


    /**
     * Unlink a view from this presenter
     *
     * @param view view to unlink
     */
    protected void unlinkView(@NonNull final T view) {
        if (this.viewRef.get() == view) {
            this.viewRef.clear();
        }
    }

    /**
     * @return the currently linked view. Null if no view is linked
     */
    protected T getView() {
        return viewRef.get();
    }


    /**
     * @return - true if the view is linked. False any other case.
     */
    protected boolean isViewLinked() {
        return viewRef != null && viewRef.get() != null;
    }

    /**
     * @return - the instance of MoviesPreviewApi to be used by the presenter
     */
    protected MoviesPreviewApi getApiInstance() {
        return utilityWrapper.getApiInstance();
    }


    /**
     * @return - the instance of FlowResolver to be used by the presenter.
     */
    protected FlowResolver getFlowResolverInstance() {
        return utilityWrapper.getFlowResolver();
    }


    /**
     * @return - the instance of MoviesContext hold by the presenter.
     */
    protected MoviesContext getContext() {
        return mContext;
    }
}
