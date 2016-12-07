package com.jpp.moviespreview.core.animations;

import android.os.Build;

import com.jpp.moviespreview.home.HomeScreen;

import javax.inject.Singleton;

import dagger.Component;
import dagger.Module;
import dagger.Provides;

/**
 * Created by jpperetti on 7/12/16.
 */

@Singleton
@Component(modules = {AnimationDiComponent.AnimationModule.class})
public interface AnimationDiComponent {


    void injectAnimations(HomeScreen presenter);


    @Module
    public class AnimationModule {

        @Provides
        SplashAnimation splashAnimation() {
            SplashAnimation splashAnimation = null;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                splashAnimation = new CircularInitialAnimation();
            } else {
                splashAnimation = new FadeInitialAnimation();
            }
            return splashAnimation;
        }

    }
}
