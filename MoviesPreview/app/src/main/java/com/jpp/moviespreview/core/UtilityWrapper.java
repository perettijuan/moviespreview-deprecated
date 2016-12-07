package com.jpp.moviespreview.core;

import com.jpp.moviespreview.core.flow.FlowResolver;
import com.jpp.moviespreview.core.network.MoviesPreviewApi;

import javax.inject.Inject;

/**
 * A wrapper clas to wrap up all the utilities that any interactor needs.
 * <p>
 * Created by jpperetti on 8/2/16.
 */
public class UtilityWrapper {

    @Inject
    MoviesPreviewApi api;


    @Inject
    FlowResolver flowResolver;


    /**
     * @return - the instance of MoviesPreviewApi for the app.
     */
    public MoviesPreviewApi getApiInstance() {
        return api;
    }


    /**
     * @return - the instance of FlowResolver
     */
    public FlowResolver getFlowResolver() {
        return flowResolver;
    }


}
