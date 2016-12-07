package com.jpp.moviespreview.core.interactors;

import android.support.annotation.NonNull;

import com.jpp.moviespreview.home.HomePresenter;
import com.jpp.moviespreview.splash.SplashPresenter;

import javax.inject.Singleton;

import dagger.Component;

/**
 * {@link Component} to inject UseCases into the Presenter layer.
 * <p>
 * Created by jpperetti on 7/12/16.
 */
@Singleton
@Component(modules = {UseCaseModule.class})
public interface InteractorDiComponent {


    /**
     * Injects the UseCases needed by the SplashPresenter into the given instance.
     *
     * @param presenter - the instance of SplashPresenter to be injected.
     */
    void inject(@NonNull SplashPresenter presenter);


    /**
     * Injects the UseCases needed by the HomePresenter into the given instance.
     *
     * @param presenter - the instance of HomePresenter to be injected.
     */
    void inject(@NonNull HomePresenter presenter);
}
