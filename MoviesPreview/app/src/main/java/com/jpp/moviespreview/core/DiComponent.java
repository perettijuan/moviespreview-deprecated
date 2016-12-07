package com.jpp.moviespreview.core;

import com.jpp.moviespreview.core.flow.FlowModule;
import com.jpp.moviespreview.core.network.RestModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Dependency injection component to inject instances in the network package.
 * <p>
 * Created by jpperetti on 12/2/16.
 */

@Singleton
@Component(modules = {RestModule.class, FlowModule.class})
public interface DiComponent {

    void inject(UtilityWrapper wrapper);

}
