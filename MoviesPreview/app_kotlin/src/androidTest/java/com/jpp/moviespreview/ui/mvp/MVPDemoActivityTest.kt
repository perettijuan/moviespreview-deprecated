package com.jpp.moviespreview.ui.mvp

import android.support.test.espresso.Espresso.*
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.rule.ActivityTestRule
import com.jpp.moviespreview.OrientationChangeAction
import com.jpp.moviespreview.core.mvp.MVPDemoActivity
import org.junit.Rule
import org.junit.Test
import org.junit.Assert.assertEquals

class MVPDemoActivityTest {

    @get:Rule val activityRule = ActivityTestRule(MVPDemoActivity::class.java)

    /**
     * Just check that activity rotated several times updates correctly the presenter
     */
    @Test fun rotate_several_times() {
        onView(isRoot()).perform(OrientationChangeAction.orientationLandscape())
        onView(isRoot()).perform(OrientationChangeAction.orientationPortrait())
        onView(isRoot()).perform(OrientationChangeAction.orientationLandscape())
        onView(isRoot()).perform(OrientationChangeAction.orientationPortrait())
        onView(isRoot()).perform(OrientationChangeAction.orientationLandscape())
        onView(isRoot()).perform(OrientationChangeAction.orientationPortrait())

        assertEquals(6, activityRule.activity.getPresenterInstance().links)
        assertEquals(5, activityRule.activity.getPresenterInstance().unlinks)
    }
}