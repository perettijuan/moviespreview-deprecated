package com.jpp.moviespreview.ui.mvp

import android.app.Activity
import android.content.pm.ActivityInfo
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.lifecycle.ActivityLifecycleCallback
import android.support.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import android.support.test.runner.lifecycle.Stage
import com.jpp.moviespreview.core.mvp.MVPDemoActivity
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import java.util.concurrent.CountDownLatch

class MVPDemoActivityTest {

    @get:Rule val activityRule = ActivityTestRule(MVPDemoActivity::class.java)

    /**
     * Just check that activity rotated several times updates correctly the presenter
     */
    @Test fun rotate_several_times() {
        rotateToLandscape()
        rotateToPortrait()

        rotateToLandscape()
        rotateToPortrait()

        rotateToLandscape()
        rotateToPortrait()

        rotateToLandscape()
        rotateToPortrait()

        // first resume -> link = 1; 8 rotations = link = 9 (1+8)
        assertEquals(9, activityRule.activity.getPresenterInstance().links)
        assertEquals(8, activityRule.activity.getPresenterInstance().unlinks)
    }

    private fun rotateToLandscape() {
        changeOrientationTo(activityRule.activity, ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE)
    }

    private fun rotateToPortrait() {
        changeOrientationTo(activityRule.activity, ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
    }


    private fun changeOrientationTo(activity: Activity, targetOrientation: Int) {
        val waitForResumed = WaitForResumed()
        activityRule.activity.runOnUiThread {
            val currentOrientation = activity.requestedOrientation

            if (targetOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
                throw IllegalStateException("Unspecified orientation not allowed")
            }

            if (currentOrientation == targetOrientation) {
                throw IllegalStateException("Already in orientation: $targetOrientation")
            }

            waitForResumed.register()

            activity.requestedOrientation = targetOrientation
        }

        waitForResumed.awaitResumed()

        activityRule.activity.runOnUiThread { waitForResumed.unregister() }
    }


    private class WaitForResumed : ActivityLifecycleCallback {

        val mCountdownLatch = CountDownLatch(1)

        override fun onActivityLifecycleChanged(activity: Activity?, stage: Stage?) {
            if (stage != Stage.RESUMED) {
                return
            }
            mCountdownLatch.countDown()
        }

        fun register() {
            ActivityLifecycleMonitorRegistry.getInstance().addLifecycleCallback(this)
        }

        fun unregister() {
            ActivityLifecycleMonitorRegistry.getInstance().removeLifecycleCallback(this)
        }

        fun awaitResumed() {
            mCountdownLatch.await()
        }
    }
}