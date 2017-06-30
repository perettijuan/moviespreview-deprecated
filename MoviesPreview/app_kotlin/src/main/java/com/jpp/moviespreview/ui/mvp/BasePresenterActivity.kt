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
abstract class BasePresenterActivity<V : PresentingView, out T : BasePresenter<V>> : AppCompatActivity(), PresentingView {


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
    private fun createStateFragmentIfNeeded(): StateFragment<V, T> {
        var stateFragment = getStateFragment()
        if (stateFragment == null) {
            stateFragment = StateFragment<V, T>()
            val fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.add(stateFragment, StateFragment.Companion.TAG)
            fragmentTransaction.commit()
        }
        return stateFragment
    }

    /**
     * Find the StateFragment instance attached to this activity (if any)
     */
    private fun getStateFragment(): StateFragment<V, T>? {
        return supportFragmentManager.findFragmentByTag(StateFragment.Companion.TAG) as StateFragment<V, T>?
    }


    /**
     * Returns a PresentingView instance (usually, the Activity)
     */
    protected abstract fun getView(): V

    /**
     * Finds the presenter in the current Fragment stack
     */
    protected fun getPresenter(): T {
        val stateFragment = getStateFragment() ?: throw IllegalStateException("StateFragment not created yet")
        val presenter = stateFragment.mPresenter ?: throw IllegalArgumentException("Presenter not attached yet")
        return presenter
    }

    /**
     * Creates the Presenter instance of the MVP
     */
    protected abstract fun createPresenter(): T
}