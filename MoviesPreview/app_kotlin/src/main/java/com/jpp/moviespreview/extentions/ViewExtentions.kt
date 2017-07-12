package com.jpp.moviespreview.extentions

import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
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

/**
 * Extension function for the RecyclerView class that allows detecting endless scrolling.
 * It will add a scrolling listener to the RecyclerView and execute the thresholdReached function
 * when the scrolling reaches the threshold
 */
fun RecyclerView.endlessScrolling(thresholdReached: () -> Unit, threshold: Int = 5) {
    addOnScrollListener(EndlessScrollListener(threshold, thresholdReached))
}


/**
 * Inner definition of a RecyclerView.OnScrollListener that evaluates the scrolling and executes
 * a function if a threshold is reached.
 */
private class EndlessScrollListener(val threshold: Int,
                                    val thresholdReached: () -> Unit) : RecyclerView.OnScrollListener() {
    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        if (dy > 0) {
            // only works with linear layout managers
            val layoutManager = recyclerView?.layoutManager as? LinearLayoutManager ?: return

            val visibleItemCount = layoutManager.childCount
            val pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition()
            if ((visibleItemCount + pastVisibleItems) >= recyclerView.adapter.itemCount - threshold) {
                thresholdReached()
            }
        }
    }
}