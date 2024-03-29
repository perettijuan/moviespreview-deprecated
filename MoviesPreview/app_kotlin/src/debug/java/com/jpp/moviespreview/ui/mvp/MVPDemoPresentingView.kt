package com.jpp.moviespreview.ui.mvp

/**
 * Created by jpp on 6/20/17.
 */

interface MVPDemoPresentingView : PresentingView {

    /**
     * Shows a list of text strings
     */
    fun showTextList(stringList: List<String>)

}