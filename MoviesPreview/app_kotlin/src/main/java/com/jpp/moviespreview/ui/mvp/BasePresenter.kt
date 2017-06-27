package com.jpp.moviespreview.ui.mvp

import android.os.Bundle
import com.jpp.moviespreview.ui.MoviesContext
import java.lang.ref.WeakReference

/**
 * Presenter base part of the MVP pattern.
 *
 * Created by jpp on 6/19/17.
 */
abstract class BasePresenter<T : com.jpp.moviespreview.core.mvp.PresentingView> {

    // weak reference to the view instance - it can be null
    private var viewRef: WeakReference<T>? = null

    var mContext: MoviesContext? = null

    /**
     * Links the provided PresentingView instance to this presenter.
     * Return type = Unit (none)
     */
    open fun linkView(viewInstance: T) {
        viewRef = WeakReference(viewInstance)
    }


    /**
     * Called at the beginning of the flow to initialize the Presenter.
     */
    open fun init(savedInstanceState: Bundle?) {
        mContext = savedInstanceState?.getParcelable(MoviesContext.Companion.Companion.EXTRA_KEY) ?: MoviesContext()
    }


    /**
     * Unlink a view from this presenter
     */
    open fun unlinkView(viewInstance: T) {
        if (getView() == viewInstance) {
            viewRef?.clear()
        }
    }

    /**
     * Returns the currently linked view. Null if no view is linked
     */
    protected fun getView() = viewRef?.get()
}