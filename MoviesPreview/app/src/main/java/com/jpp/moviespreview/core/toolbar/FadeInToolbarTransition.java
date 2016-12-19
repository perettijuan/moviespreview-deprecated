package com.jpp.moviespreview.core.toolbar;

import android.support.transition.AutoTransition;
import android.support.transition.Transition;

/**
 * Helper class to create a transition for the fade in effect.
 * <p>
 * Created by jpperetti on 19/12/16.
 */
public class FadeInToolbarTransition extends AutoTransition {

    private static final int FADE_IN_DURATION = 200;

    private FadeInToolbarTransition() {
        // force factory method
    }

    /**
     * Creates an instance of Transition that can be used to fade in the search toolbar.
     *
     * @return - the created Transition.
     */
    public static Transition createTransition() {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(FADE_IN_DURATION);
        return transition;
    }
}
