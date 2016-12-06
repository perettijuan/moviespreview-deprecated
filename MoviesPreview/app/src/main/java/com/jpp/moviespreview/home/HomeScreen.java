package com.jpp.moviespreview.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;
import com.jpp.moviespreview.core.util.RecyclerViewItemClickListener;
import com.jpp.moviespreview.home.adapter.MoviesRecyclerViewAdapter;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;

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

    private MoviesRecyclerViewAdapter adapter;

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
        rvMovies.setLayoutManager(new LinearLayoutManager(this));
        rvMovies.addOnItemTouchListener(new RecyclerViewItemClickListener(this, new RecyclerViewItemClickListener.OnRecyclerViewItemClickListener() {
            @Override
            public void onRecyclerViewItemClick(@NonNull RecyclerView parent, @NonNull View view, int adapterPosition, long id) {
                getPresenter().onMovieItemSelected(adapter.getItemAtPosition(adapterPosition));
            }
        }));
        adapter = new MoviesRecyclerViewAdapter();
        rvMovies.setAdapter(adapter);
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
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void showMoviesPage(@NonNull List<MovieListItem> page) {
        swipeRefreshLayout.setRefreshing(false);
        adapter.swipeData(page);
    }


}
