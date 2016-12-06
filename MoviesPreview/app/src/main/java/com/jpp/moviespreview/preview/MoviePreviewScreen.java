package com.jpp.moviespreview.preview;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;

/**
 * Created by jpperetti on 6/12/16.
 */

public class MoviePreviewScreen extends BasePresenterActivity<MoviePreviewView, MoviePreviewPresenter> implements MoviePreviewView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_preview_screen);
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
        SimpleDraweeView moviePoster = (SimpleDraweeView) findViewById(R.id.iv_movie_poster);
        Uri uri = Uri.parse(moviePreviewItem.getPosterUrl());
        moviePoster.setImageURI(uri);


        TextView txtTitle = (TextView) findViewById(R.id.txt_movie_detail_title);
        txtTitle.setText(moviePreviewItem.getTitle());

        TextView txtBody = (TextView) findViewById(R.id.txt_movie_detail_body);
        txtBody.setText(moviePreviewItem.getOverview());

        TextView txtDate = (TextView) findViewById(R.id.txt_movie_detail_date);
        txtDate.setText(moviePreviewItem.getReleaseDate());

        TextView txtPopularity = (TextView) findViewById(R.id.txt_movie_detail_popularity);
        txtPopularity.setText(moviePreviewItem.getPopularity());
    }
}
