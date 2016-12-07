package com.jpp.moviespreview.core.animations;

import android.animation.ObjectAnimator;
import android.support.annotation.NonNull;
import android.view.View;

/**
 * Created by jpperetti on 7/12/16.
 */

public class FadeInitialAnimation implements SplashAnimation {
    @Override
    public void animateView(@NonNull View splashView) {
        ObjectAnimator.ofFloat(splashView, View.ALPHA, 1, 0).start();
    }
}
