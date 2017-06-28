package com.jpp.moviespreview.ui.mvp

import android.os.Bundle
import android.support.annotation.CallSuper
import com.jpp.moviespreview.ui.MoviesContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference

/**
 * Presenter base part of the MVP pattern.
 *
 * Created by jpp on 6/19/17.
 */
abstract class BasePresenter<T : PresentingView> {

    // weak reference to the view instance - it can be null
    private var mViewRef: WeakReference<T>? = null

    lateinit var mContext: MoviesContext

    /**
     * Links the provided PresentingView instance to this presenter.
     * Return type = Unit (none)
     */
    @CallSuper
    open fun linkView(viewInstance: T) {
        mViewRef = WeakReference(viewInstance)
    }


    /**
     * Called at the beginning of the flow to initialize the Presenter.
     */
    @CallSuper
    open fun init(savedInstanceState: Bundle?) {
        mContext = savedInstanceState?.getParcelable(MoviesContext.Companion.EXTRA_KEY) ?: MoviesContext()
    }


    /**
     * Unlink a view from this presenter
     */
    @CallSuper
    open fun unlinkView(viewInstance: T) {
        if (getView() == viewInstance) {
            mViewRef?.clear()
        }
    }

    /**
     * Returns the currently linked view. Null if no view is linked
     */
    protected fun getView() = mViewRef?.get()


    fun <Response> executeInBackground(background: () -> Response?, ui: (Response?) -> Unit) {
        doAsync {
            val result = background()
            uiThread {
                ui(result)
            }
        }
    }


}