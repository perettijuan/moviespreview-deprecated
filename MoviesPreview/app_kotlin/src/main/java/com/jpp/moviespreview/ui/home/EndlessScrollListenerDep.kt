package com.jpp.moviespreview.ui.home

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

/**
 * Created by jpp on 7/7/17.
 */
class EndlessScrollListenerDep(val threshold: Int = 5,
                               val thresholdReached: () -> Unit) : RecyclerView.OnScrollListener() {


    override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        // only works with linear layout managers
        val layoutManager = recyclerView?.layoutManager as? LinearLayoutManager ?: return

        if (dy > 0) {
            val visibleItemCount = layoutManager.childCount
            val pastVisibleItems = layoutManager.findFirstCompletelyVisibleItemPosition()
            // 5 is the offset
            if ((visibleItemCount + pastVisibleItems) >= recyclerView.adapter.itemCount - threshold) {
                thresholdReached()
            }
        }

    }
}