package com.jpp.moviespreview.preview;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by jpperetti on 6/12/16.
 */

public class MoviePreviewScreen extends BasePresenterActivity<MoviePreviewView, MoviePreviewPresenter>
        implements MoviePreviewView, AppBarLayout.OnOffsetChangedListener {

    private static final float OFFSET_TO_SHOW_TITLE_AT_TOOLBAR = 0.9f;
    private static final float OFFSET_TO_HIDE_TITLE_DETAILS = 0.3f;
    private static final int ALPHA_ANIMATIONS_DURATION = 200;

    @InjectView(R.id.preview_toolbar)
    Toolbar previewToolbar;

    @InjectView(R.id.preview_title_container)
    LinearLayout titleContainer;

    @InjectView(R.id.preview_txt_title)
    TextView toolbarTitle;

    @InjectView(R.id.preview_txt_movie_title)
    TextView txtMovieTitle;

    @InjectView(R.id.preview_app_bar_layout)
    AppBarLayout appBarLayout;

    @InjectView(R.id.iv_movie_back)
    SimpleDraweeView ivMovieBack;

    @InjectView(R.id.iv_movie_poster)
    SimpleDraweeView ivMoviePoster;


    private boolean mIsTheTitleVisible = false;
    private boolean mIsTheTitleContainerVisible = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_preview_screen_v2);
        ButterKnife.inject(this);

        appBarLayout.addOnOffsetChangedListener(this);
        startAlphaAnimation(toolbarTitle, 0, View.INVISIBLE);
    }

    @NonNull
    @Override
    protected MoviePreviewView getView() {
        return this;
    }

    @NonNull
    @Override
    protected MoviePreviewPresenter createPresenter() {
        return new MoviePreviewPresenter();
    }

    @Override
    public void showMovie(@NonNull MoviePreviewItem moviePreviewItem) {
        Uri backdropUri = Uri.parse(moviePreviewItem.getBackdropUrl());
        ivMovieBack.setImageURI(backdropUri);

        Uri moviePoster = Uri.parse(moviePreviewItem.getPosterUrl());
        ivMoviePoster.setImageURI(moviePoster);

        txtMovieTitle.setText(moviePreviewItem.getTitle());
        toolbarTitle.setText(moviePreviewItem.getTitle());

        /*SimpleDraweeView moviePoster = (SimpleDraweeView) findViewById(R.id.iv_movie_poster);
        Uri uri = Uri.parse(moviePreviewItem.getPosterUrl());
        moviePoster.setImageURI(uri);


        TextView toolbarTitle = (TextView) findViewById(R.id.txt_movie_detail_title);
        toolbarTitle.setText(moviePreviewItem.getTitle());

        TextView txtBody = (TextView) findViewById(R.id.txt_movie_detail_body);
        txtBody.setText(moviePreviewItem.getOverview());

        TextView txtDate = (TextView) findViewById(R.id.txt_movie_detail_date);
        txtDate.setText(moviePreviewItem.getReleaseDate());

        TextView txtPopularity = (TextView) findViewById(R.id.txt_movie_detail_popularity);
        txtPopularity.setText(moviePreviewItem.getPopularity());*/
    }


    /**
     * Perform a simple alpha animation to show/hide a view.
     */
    private void startAlphaAnimation(View v, long duration, int visibility) {
        AlphaAnimation alphaAnimation = (visibility == View.VISIBLE)
                ? new AlphaAnimation(0f, 1f)
                : new AlphaAnimation(1f, 0f);

        alphaAnimation.setDuration(duration);
        alphaAnimation.setFillAfter(true);
        v.startAnimation(alphaAnimation);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int maxScroll = appBarLayout.getTotalScrollRange();
        float percentage = (float) Math.abs(verticalOffset) / (float) maxScroll;

        handleAlphaOnTitle(percentage);
        handleToolbarTitleVisibility(percentage);
    }


    private void handleToolbarTitleVisibility(float percentage) {
        if (percentage >= OFFSET_TO_SHOW_TITLE_AT_TOOLBAR) {
            if (!mIsTheTitleVisible) {
                startAlphaAnimation(toolbarTitle, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleVisible = true;
            }
        } else {
            if (mIsTheTitleVisible) {
                startAlphaAnimation(toolbarTitle, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleVisible = false;
            }
        }
    }

    private void handleAlphaOnTitle(float percentage) {
        if (percentage >= OFFSET_TO_HIDE_TITLE_DETAILS) {
            if (mIsTheTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.INVISIBLE);
                mIsTheTitleContainerVisible = false;
            }
        } else {
            if (!mIsTheTitleContainerVisible) {
                startAlphaAnimation(titleContainer, ALPHA_ANIMATIONS_DURATION, View.VISIBLE);
                mIsTheTitleContainerVisible = true;
            }
        }
    }
}
