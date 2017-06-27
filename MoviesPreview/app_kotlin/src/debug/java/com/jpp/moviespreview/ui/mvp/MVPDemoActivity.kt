package com.jpp.moviespreview.core.mvp

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.jpp.moviespreview.R
import com.jpp.moviespreview.ui.mvp.BasePresenterActivity
import com.jpp.moviespreview.ui.mvp.MVPDemoAdapter
import com.jpp.moviespreview.ui.mvp.MVPDemoPresentingView
import org.jetbrains.anko.find

/**
 * Created by jpp on 6/20/17.
 */
class MVPDemoActivity : BasePresenterActivity<MVPDemoPresentingView, MVPDemoPresenter>(), MVPDemoPresentingView {

    var demoList: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mvp_demo_activity)

        demoList = find(R.id.demoList)
        demoList?.layoutManager = LinearLayoutManager(this)

    }

    override fun showTextList(stringList: List<String>) {
        demoList?.adapter = MVPDemoAdapter(stringList)
    }


    override fun getView(): MVPDemoPresentingView {
        return this
    }

    override fun createPresenter(): MVPDemoPresenter {
        return MVPDemoPresenter()
    }

    fun getPresenterInstance() : MVPDemoPresenter {
        return getPresenter()
    }
}