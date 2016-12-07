package com.jpp.moviespreview.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jakewharton.rxbinding.support.v7.widget.RecyclerViewScrollEvent;
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.animations.AnimationDiComponent;
import com.jpp.moviespreview.core.animations.DaggerAnimationDiComponent;
import com.jpp.moviespreview.core.animations.SplashAnimation;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;
import com.jpp.moviespreview.core.util.RecyclerViewItemClickListener;
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

    @InjectView(R.id.swipe_main_screen)
    SwipeRefreshLayout swipeRefreshLayout;

    @InjectView(R.id.tb_main_screen)
    Toolbar tbMainScreen;

    @InjectView(R.id.rv_movies)
    RecyclerView rvMovies;


    @Inject
    SplashAnimation splashAnimation;

    private MoviesRecyclerViewAdapter mRecyclerAdapter;
    private LinearLayoutManager mLayoutManager;
    private int mTotalItemCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        ButterKnife.inject(this);
        prepareSwipeView();
        setSupportActionBar(tbMainScreen);
        prepareRecyclerView();
    }


    /**
     * Fix the problem of the Swipe view not showing the first time.
     * Source: http://stackoverflow.com/questions/26858692/swiperefreshlayout-setrefreshing-not-showing-indicator-initially
     */
    private void prepareSwipeView() {
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary, R.color.colorAccent);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //TODO
            }
        });
    }


    private void prepareRecyclerView() {
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
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showMoviesPage(@NonNull List<MovieListItem> page) {
        if (swipeRefreshLayout.isRefreshing()) {
            swipeRefreshLayout.setRefreshing(false);
        }

        mRecyclerAdapter.swipeData(page);
        mTotalItemCount = mRecyclerAdapter.getItemCount();
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


}
