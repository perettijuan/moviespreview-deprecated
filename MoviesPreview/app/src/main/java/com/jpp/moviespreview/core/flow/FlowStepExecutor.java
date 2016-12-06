package com.jpp.moviespreview.core.flow;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;

/**
 * Takes care of executing the passing between steps in the flow.
 * <p>
 * Created by jpperetti on 3/12/16.
 */

public interface FlowStepExecutor {

    /**
     * Executes the next step wrapped in the intent passed as parameter.
     *
     * @param intent - the Intent to execute the next step.
     */
    void executeNextStep(@NonNull Intent intent);

    /**
     * @return - a Context instance.
     */
    @NonNull
    Context getApplicationContext();
}
