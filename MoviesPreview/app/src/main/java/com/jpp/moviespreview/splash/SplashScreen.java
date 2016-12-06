package com.jpp.moviespreview.splash;

import android.os.Bundle;
import android.support.annotation.NonNull;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.jpp.moviespreview.R;
import com.jpp.moviespreview.core.mvp.BasePresenterActivity;

/**
 * Splash activity.
 * <p>
 * Created by jpperetti on 15/2/16.
 */
public class SplashScreen extends BasePresenterActivity<SplashView, SplashPresenter> implements SplashView {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);
    }

    @NonNull
    @Override
    protected SplashView getView() {
        return this;
    }

    @NonNull
    @Override
    protected SplashPresenter createPresenter() {
        return new SplashPresenter();
    }


}
