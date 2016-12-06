package com.jpp.moviespreview.core.flow;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module to provide flow instances.
 * <p>
 * Created by jpperetti on 3/12/16.
 */
@Module
public class FlowModule {

    @Provides
    @Singleton
    public FlowResolver flowResolver() {
        return new FlowResolver();
    }

}
