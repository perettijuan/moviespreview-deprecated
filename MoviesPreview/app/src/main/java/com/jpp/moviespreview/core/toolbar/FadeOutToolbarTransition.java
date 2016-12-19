package com.jpp.moviespreview.core.toolbar;

import android.support.annotation.NonNull;
import android.support.transition.AutoTransition;
import android.support.transition.Transition;

/**
 * Helper class to create a transition for the fade out effect.
 * <p>
 * Created by jpperetti on 18/12/16.
 */
public class FadeOutToolbarTransition extends AutoTransition {

    private static final int FADE_OUT_DURATION = 250;

    private FadeOutToolbarTransition() {
        // force factory method
    }


    /**
     * Creates an AutoTransition that calls the {@link android.support.transition.Transition.TransitionListener#onTransitionEnd(Transition)}
     * of the given listener when the transition is done.
     *
     * @param finishingAction - the listener to call when transition is done.
     * @return - the AutoTransition created.
     */
    public static Transition withAction(@NonNull TransitionListener finishingAction) {
        AutoTransition transition = new AutoTransition();
        transition.setDuration(FADE_OUT_DURATION);
        transition.addListener(finishingAction);
        return transition;
    }

}
