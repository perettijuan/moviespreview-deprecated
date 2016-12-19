package com.jpp.moviespreview.search;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.view.MenuItem;
import android.view.ViewTreeObserver;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;
import com.jpp.moviespreview.core.toolbar.FadeInToolbarTransition;
import com.jpp.moviespreview.core.toolbar.FadeOutToolbarTransition;
import com.jpp.moviespreview.core.toolbar.SimpleTransitionListener;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jpperetti on 19/12/16.
 */

public class SearchScreen extends BasePresenterActivity<SearchView, SearchPresenter> implements SearchView {

    @InjectView(R.id.tb_search_screen)
    SearchToolbar searchToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_screen);
        ButterKnife.inject(this);
        setSupportActionBar(searchToolbar);

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

    // MVP

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
