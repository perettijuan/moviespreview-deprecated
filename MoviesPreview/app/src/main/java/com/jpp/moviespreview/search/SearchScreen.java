package com.jpp.moviespreview.search;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.core.mvp.BasePresenterActivity;

/**
 * Created by jpperetti on 19/12/16.
 */

public class SearchScreen extends BasePresenterActivity<SearchView, SearchPresenter> implements SearchView {



    @NonNull
    @Override
    protected SearchView getView() {
        return this;
    }

    @NonNull
    @Override
    protected SearchPresenter createPresenter() {
        return new SearchPresenter();
    }
}
