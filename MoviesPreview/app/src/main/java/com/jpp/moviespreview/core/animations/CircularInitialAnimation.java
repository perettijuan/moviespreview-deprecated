package com.jpp.moviespreview.core.animations;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewAnimationUtils;

/**
 * Created by jpperetti on 7/12/16.
 */

/* package */ class CircularInitialAnimation implements SplashAnimation {


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void animateView(final @NonNull View splashView) {
        splashView.addOnLayoutChangeListener(new View.OnLayoutChangeListener() {
            @Override
            public void onLayoutChange(View view, int i, int i1, int i2, int i3, int i4, int i5, int i6, int i7) {
                final int cx = (splashView.getLeft() + splashView.getRight()) / 2;
                final int cy = (splashView.getTop() + splashView.getBottom()) / 2;

                final int initialRadius = splashView.getWidth();

                splashView.removeOnLayoutChangeListener(this);
                Animator animator = ViewAnimationUtils.createCircularReveal(splashView, cx, cy, initialRadius, 0);
                animator.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        splashView.setVisibility(View.GONE);
                    }
                });
                animator.setDuration(500);
                animator.start();
            }
        });
    }

}
