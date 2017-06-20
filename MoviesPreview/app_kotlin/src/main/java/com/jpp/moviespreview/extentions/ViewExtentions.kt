package com.jpp.moviespreview.extentions

import android.content.Context
import android.view.View

/**
 * Contains all the extension functions for View class (and subclasses)
 *
 * Created by jpp on 6/20/17.
 */

/**
 * Extension function for the View class : returns the Context of the View
 */
val View.ctx: Context
    get() = context