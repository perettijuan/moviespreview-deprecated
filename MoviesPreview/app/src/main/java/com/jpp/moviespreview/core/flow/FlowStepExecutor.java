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
     * @param intent          - the Intent to execute the next step.
     * @param maintainCurrent - true to indicate that the current step should be maintained in the stack, false any other case.
     */
    void executeNextStep(@NonNull Intent intent, boolean maintainCurrent);

    /**
     * @return - a Context instance.
     */
    @NonNull
    Context getApplicationContext();
}
