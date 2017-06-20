package com.jpp.moviespreview.core.mvp

import android.os.Bundle
import com.jpp.moviespreview.core.MoviesContext
import java.lang.ref.WeakReference

/**
 * Presenter base part of the MVP pattern.
 *
 * Created by jpp on 6/19/17.
 */
abstract class BasePresenter<T : PresentingView> {

    // weak reference to the view instance - it can be null
    private var viewRef: WeakReference<T>? = null

    var mContext: MoviesContext? = null

    /**
     * Links the provided PresentingView instance to this presenter.
     * Return type = Unit (none)
     */
    fun linkView(viewInstance: T) {
        viewRef = WeakReference(viewInstance)
    }


    /**
     * Called at the beginning of the flow to initialize the Presenter.
     */
    fun init(savedInstanceState: Bundle?) : BasePresenter<T> {
        mContext = savedInstanceState?.getParcelable(MoviesContext.EXTRA_KEY) ?: MoviesContext()
        return this
    }


    /**
     * Unlink a view from this presenter
     */
    fun unlinkView(viewInstance: T) {
        if (viewRef?.get() == viewInstance) {
            viewRef?.clear()
        }
    }

    /**
     * Returns the currently linked view. Null if no view is linked
     */
    protected fun getView() = viewRef?.get()
}