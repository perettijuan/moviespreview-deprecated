package com.jpp.moviespreview.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;
import com.jpp.moviespreview.core.toolbar.FadeInToolbarTransition;
import com.jpp.moviespreview.core.toolbar.FadeOutToolbarTransition;
import com.jpp.moviespreview.core.toolbar.SimpleTransitionListener;
import com.jpp.moviespreview.core.ui.SuperLoadingView;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * BasePresenterActivity for the search flow.
 * Implements SearchView and provides a SearchPresenter implementation for the MVP.
 * <p>
 * Created by jpperetti on 19/12/16.
 */
public class SearchScreen extends BasePresenterActivity<SearchView, SearchPresenter> implements SearchView {

    @InjectView(R.id.tb_search_screen)
    SearchToolbar searchToolbar;

    @InjectView(R.id.rv_search_movies)
    RecyclerView rvSearchMovies;

    @InjectView(R.id.search_superloading_view)
    SuperLoadingView searchSuperloadingView;

    private SearchMovieAdapter rvAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        ButterKnife.inject(this);
        setSupportActionBar(searchToolbar);
        prepareRecyclerView();

        searchToolbar.setActionListener(new SearchToolbar.SearchActionListener() {
            @Override
            public void onSearchEntered(@NonNull String query) {
                getPresenter().searchMovie(query);
            }
        });

        // Only if it's the first time.
        if (savedInstanceState == null) {
            configureEnterAnimation();
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void finish() {
        hideKeyboard();

        finishTransitionWithAction(new Runnable() {
            @Override
            public void run() {
                SearchScreen.super.finish();
                overridePendingTransition(0, 0);
            }
        });
    }


    /**
     * Prepares the recycler view that shows the results.
     */
    private void prepareRecyclerView() {
        rvSearchMovies.setLayoutManager(new LinearLayoutManager(this));
        rvAdapter = new SearchMovieAdapter();
        rvSearchMovies.setAdapter(rvAdapter);
    }


    // transitions

    /**
     * Configures the enter animation.
     */
    private void configureEnterAnimation() {
        searchToolbar.hideContent();
        ViewTreeObserver viewTreeObserver = searchToolbar.getViewTreeObserver();
        viewTreeObserver.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                searchToolbar.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                // show the search
                TransitionManager.beginDelayedTransition(searchToolbar, FadeInToolbarTransition.createTransition());
                searchToolbar.requestFocusOnSearch();
                searchToolbar.showContent();
            }
        });
    }


    /**
     * Finishes the screen with the exit transition.
     */
    private void finishTransitionWithAction(final Runnable action) {
        Transition transition = FadeOutToolbarTransition.withAction(new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                action.run();
            }
        });

        TransitionManager.beginDelayedTransition(searchToolbar, transition);
        searchToolbar.hideContent();
    }

    //-- MVP

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


    //-- SearchView
    @Override
    public void showLoading() {
        searchSuperloadingView.onStart();
        searchSuperloadingView.setVisibility(View.VISIBLE);
        rvSearchMovies.setVisibility(View.GONE);
        hideKeyboard();
    }

    @Override
    public void dismissLoading() {
        searchSuperloadingView.onStop();
        searchSuperloadingView.setVisibility(View.GONE);
    }

    @Override
    public void showSearchResult(@NonNull List<SearchMovieListItem> result) {
        rvAdapter.swipeData(result);
        rvSearchMovies.setVisibility(View.VISIBLE);
    }
}
