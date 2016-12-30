package com.jpp.moviespreview.home;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.animations.AnimationDiComponent;
import com.jpp.moviespreview.core.animations.DaggerAnimationDiComponent;
import com.jpp.moviespreview.core.animations.SplashAnimation;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;
import com.jpp.moviespreview.core.toolbar.FadeInToolbarTransition;
import com.jpp.moviespreview.core.toolbar.FadeOutToolbarTransition;
import com.jpp.moviespreview.core.toolbar.SimpleTransitionListener;
import com.jpp.moviespreview.core.ui.SuperLoadingView;
import com.jpp.moviespreview.core.util.RecyclerViewItemClickListener;
import com.jpp.moviespreview.home.adapter.HomeMenuRecyclerViewAdapter;
import com.jpp.moviespreview.home.adapter.MoviesRecyclerViewAdapter;

import java.util.List;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.InjectView;
import rx.Observable;

/**
 * Activity for the home screen.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
public class HomeScreen extends BasePresenterActivity<HomeView, HomePresenter> implements HomeView {

    @InjectView(R.id.home_superloading_view)
    SuperLoadingView homeSuperloadingView;

    @InjectView(R.id.tb_main_screen)
    Toolbar tbMainScreen;

    @InjectView(R.id.rv_movies)
    RecyclerView rvMovies;

    @InjectView(R.id.rv_home_menu)
    RecyclerView rvHomeMenu;

    @InjectView(R.id.drw_home)
    DrawerLayout drwHome;

    @InjectView(R.id.tb_main_screen_search)
    HomeToolbar tbMainScreenSearch;

    @Inject
    SplashAnimation splashAnimation;

    private MoviesRecyclerViewAdapter mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;
    private int mTotalItemCount;

    private HomeMenuRecyclerViewAdapter mMenuAdapter;
    private ActionBarDrawerToggle mDrawerToggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        ButterKnife.inject(this);
        setSupportActionBar(tbMainScreen);
        prepareRecyclerView();
        prepareDrawer();
        prepareSearchToolbar();
    }

    @Override
    protected void onResume() {
        super.onResume();
        fadeToolbarIn();
    }


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //-- prepare


    /**
     * Prepares the recycler views shown in the home screen.
     */
    private void prepareRecyclerView() {
        // menu first
        rvHomeMenu.setLayoutManager(new LinearLayoutManager(this));
        rvHomeMenu.addOnItemTouchListener(new RecyclerViewItemClickListener(this, new RecyclerViewItemClickListener.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(@NonNull RecyclerView parent, @NonNull View view, int adapterPosition, long id) {
                getPresenter().onHomeMenuItemSelected(mMenuAdapter.getItemAtPosition(adapterPosition), mMenuAdapter.getData());
                drwHome.closeDrawer(Gravity.LEFT);
            }
        }));
        mMenuAdapter = new HomeMenuRecyclerViewAdapter();
        rvHomeMenu.setAdapter(mMenuAdapter);


        // movies then
        mLayoutManager = new LinearLayoutManager(this);
        rvMovies.setLayoutManager(mLayoutManager);
        rvMovies.addOnItemTouchListener(new RecyclerViewItemClickListener(this, new RecyclerViewItemClickListener.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(@NonNull RecyclerView parent, @NonNull View view, int adapterPosition, long id) {
                getPresenter().onMovieItemSelected(mRecyclerAdapter.getItemAtPosition(adapterPosition));
            }
        }));
        mRecyclerAdapter = new MoviesRecyclerViewAdapter();
        rvMovies.setAdapter(mRecyclerAdapter);
    }


    /**
     * Prepares the drawer that shows the home menu.
     */
    private void prepareDrawer() {
        mDrawerToggle = new ActionBarDrawerToggle(this, drwHome, R.string.app_name, R.string.app_name);
        drwHome.addDrawerListener(mDrawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
    }

    /**
     * Prepares the search toolbar.
     */
    private void prepareSearchToolbar() {
        tbMainScreenSearch.setTitle(R.string.search_movies);
        tbMainScreenSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showKeyboard();
                transitionToSearch();
            }
        });
    }


    //-- Transitions

    /**
     * Perform the transition to show the toolbar.
     */
    private void fadeToolbarIn() {
        TransitionManager.beginDelayedTransition(tbMainScreenSearch, FadeInToolbarTransition.createTransition());
        LinearLayout.LayoutParams searchLayoutParams = (LinearLayout.LayoutParams) tbMainScreenSearch.getLayoutParams();
        int toolbarMargin = getResources().getDimensionPixelSize(R.dimen.search_toolbar_margin);
        searchLayoutParams.setMargins(toolbarMargin, toolbarMargin, toolbarMargin, toolbarMargin);
        tbMainScreenSearch.showContent();
        tbMainScreenSearch.setLayoutParams(searchLayoutParams);
        tbMainScreen.setVisibility(View.VISIBLE);
        rvMovies.setVisibility(View.VISIBLE);
    }


    /**
     * Perform the transition to the search view.
     */
    private void transitionToSearch() {
        Transition transition = FadeOutToolbarTransition.withAction(fadeoutTransitionListener());
        TransitionManager.beginDelayedTransition(tbMainScreenSearch, transition);
        LinearLayout.LayoutParams searchLayoutParams = (LinearLayout.LayoutParams) tbMainScreenSearch.getLayoutParams();
        searchLayoutParams.setMargins(0, 0, 0, 0);
        tbMainScreenSearch.setLayoutParams(searchLayoutParams);
        tbMainScreenSearch.hideContent();
        tbMainScreen.setVisibility(View.GONE);
        rvMovies.setVisibility(View.GONE);
    }


    /**
     * Create a TransitionListener to handle the transition effect.
     */
    private Transition.TransitionListener fadeoutTransitionListener() {
        return new SimpleTransitionListener() {
            @Override
            public void onTransitionEnd(Transition transition) {
                getPresenter().onSearchActionSelected();
                // to avoid natural animation.
                overridePendingTransition(0, 0);
            }
        };
    }


    //-- presenter

    @NonNull
    @Override
    protected HomeView getView() {
        return this;
    }

    @NonNull
    @Override
    protected HomePresenter createPresenter() {
        return new HomePresenter();
    }


    //-- HomeView

    @Override
    public void performInitialAnimations() {
        AnimationDiComponent animationDiComponent = DaggerAnimationDiComponent.builder()
                .animationModule(new AnimationDiComponent.AnimationModule())
                .build();
        animationDiComponent.injectAnimations(this);
        splashAnimation.animateView(findViewById(R.id.splash_view));
    }

    @Override
    public void showLoading() {
        homeSuperloadingView.onStart();
        homeSuperloadingView.setVisibility(View.VISIBLE);
    }

    @Override
    public void showMoviesPage(@NonNull List<MovieListItem> page) {
        if (findViewById(R.id.splash_view).getVisibility() == View.VISIBLE) {
            findViewById(R.id.splash_view).setVisibility(View.GONE);
        }

        homeSuperloadingView.onStop();
        homeSuperloadingView.setVisibility(View.GONE);

        mRecyclerAdapter.swipeData(page);
        mTotalItemCount = mRecyclerAdapter.getItemCount();
    }

    @Override
    public void clearPage() {
        mRecyclerAdapter.clear();
        mTotalItemCount = 0;
    }

    @Override
    @NonNull
    public Observable<RecyclerViewScrollEvent> getScrollingObservable() {
        return RxRecyclerView.scrollEvents(rvMovies);
    }

    @Override
    public boolean shouldLoadNewPage(int offset) {
        int visibleItemCount = mLayoutManager.getChildCount();
        int pastVisibleItems = mLayoutManager.findFirstVisibleItemPosition();
        return ((visibleItemCount + pastVisibleItems) >= (mTotalItemCount - offset));
    }

    @Override
    public void showHomeMenu(@NonNull List<HomeMenuListItem> items) {
        mMenuAdapter.updateData(items);
    }

    public void setTitle(@StringRes int title) {
        getSupportActionBar().setTitle(title);
    }

}
