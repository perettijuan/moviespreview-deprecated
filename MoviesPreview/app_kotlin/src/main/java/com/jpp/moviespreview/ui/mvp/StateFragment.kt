package com.jpp.moviespreview.ui.mvp

import android.os.Bundle
import android.support.v4.app.Fragment

/**
 * Fragment used to save the Presenter state when the activity rotates.
 *
 * Created by jpp on 6/19/17.
 */
class StateFragment<V : PresentingView, T : BasePresenter<V>> : Fragment() {

    /**
     * Complains with the contract of Fragment to declare non-params constructor
     * with factory method.
     */
    companion object {
        var TAG = StateFragment::class.simpleName
    }

    var mPresenter: T? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
    }
}