package com.jpp.moviespreview.ui.mvp

import android.support.annotation.CallSuper
import com.jpp.moviespreview.domain.UseCaseFactory
import com.jpp.moviespreview.ui.MoviesContext
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import java.lang.ref.WeakReference

/**
 * Base class for all Presenters in the application.
 * It interacts with a PresentingView instance in order to show to the user information
 * and receive input from the user.
 *
 * Created by jpp on 6/19/17.
 */
abstract class BasePresenter<T : PresentingView>(val useCaseFactory: UseCaseFactory) {


    // weak reference to the view instance - it can be null
    private var mViewRef: WeakReference<T>? = null

    var mContext = MoviesContext()

    /**
     * Links the provided PresentingView instance to this presenter.
     * Return type = Unit (none)
     */
    @CallSuper
    open fun linkView(viewInstance: T) {
        mViewRef = WeakReference(viewInstance)
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


    /**
     * Executes a function in background (background() lambda) and process the response of that
     * function on UI thread (lambda ui())
     */
    protected fun <Response> executeInBackground(background: () -> Response?, ui: (Response?) -> Unit) {
        doAsync {
            val result = background()
            uiThread {
                ui(result)
            }
        }
    }


}