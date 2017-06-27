package com.jpp.moviespreview.ui.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

/**
 * AppCompatActivity implementation for MVP - approach: use a Fragment (with retainInstance = true
 * in order to maintain the Fragment in the back stack) that is never shown to the user and it
 * attaches/detaches a BasePresenter instance to that Fragment.
 * Then, it uses the Fragment to access the presenter and execute lifecycle events.
 *
 * Created by jpp on 6/19/17.
 */
// out T : BasePresenter<V> -> ensures that the type is only returned (produced) and never consumed (there are not setters for this type)
abstract class BasePresenterActivity<V : com.jpp.moviespreview.core.mvp.PresentingView, out T : BasePresenter<V>> : AppCompatActivity(), com.jpp.moviespreview.core.mvp.PresentingView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var stateFragment = createStateFragmentIfNeeded()
        var presenter = stateFragment.mPresenter

        if (presenter == null) {
            presenter = createPresenter()
            presenter.init(savedInstanceState)
        }

        stateFragment.mPresenter = presenter
    }

    override fun onStart() {
        super.onStart()
        getPresenter().linkView(getView())
    }

    override fun onStop() {
        getPresenter().unlinkView(getView())
        super.onStop()
    }

    /**
     * Lookup for the StateFragment: First it attempts to retrieve it from the stack.
     * If not possible, creates a new instance.
     */
    private fun createStateFragmentIfNeeded(): com.jpp.moviespreview.core.mvp.StateFragment<V, T> {
        var stateFragment = getStateFragment()
        if (stateFragment == null) {
            stateFragment = com.jpp.moviespreview.core.mvp.StateFragment<V, T>()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(stateFragment, com.jpp.moviespreview.core.mvp.StateFragment.Companion.TAG)
            fragmentTransaction.commit()
        }
        return stateFragment
    }

    /**
     * Find the StateFragment instance attached to this activity (if any)
     */
    private fun getStateFragment(): com.jpp.moviespreview.core.mvp.StateFragment<V, T>? {
        return supportFragmentManager.findFragmentByTag(com.jpp.moviespreview.core.mvp.StateFragment.Companion.TAG) as com.jpp.moviespreview.core.mvp.StateFragment<V, T>?
    }


    protected abstract fun getView(): V

    protected fun getPresenter(): T {
        val stateFragment = getStateFragment() ?: throw IllegalStateException("StateFragment not created yet")
        val presenter = stateFragment.mPresenter ?: throw IllegalArgumentException("Presenter not attached yet")
        return presenter
    }

    protected abstract fun createPresenter(): T
}