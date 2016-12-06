package com.jpp.moviespreview.core.mvp;

import com.jpp.moviespreview.core.flow.FlowResolver;
import com.jpp.moviespreview.core.network.MoviesPreviewApi;

import javax.inject.Inject;

/**
 * A wrapper clas to wrap up all the utilities that presenters interact with
 * <p>
 * Created by jpperetti on 8/2/16.
 */
public class PresenterUtilityWrapper {

    @Inject
    MoviesPreviewApi api;


    @Inject
    FlowResolver flowResolver;

}
