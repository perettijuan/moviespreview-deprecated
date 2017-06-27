package com.jpp.moviespreview.core.mvp

import com.jpp.moviespreview.ui.mvp.MVPDemoPresentingView

/**
 * Created by jpp on 6/20/17.
 */
class MVPDemoPresenter : BasePresenter<MVPDemoPresentingView>() {


    val stringDataList = ArrayList<String>()
    var links = 0
    var unlinks = 0

    override fun linkView(viewInstance: MVPDemoPresentingView) {
        super.linkView(viewInstance)
        ++links
        stringDataList.add("Links = $links")
        getView()?.showTextList(stringDataList)
    }


    override fun unlinkView(viewInstance: MVPDemoPresentingView) {
        super.unlinkView(viewInstance)
        ++unlinks
        stringDataList.add("Unlinks = $unlinks")
    }


}