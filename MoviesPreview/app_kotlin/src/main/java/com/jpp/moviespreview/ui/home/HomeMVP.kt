package com.jpp.moviespreview.ui.home

import com.jpp.moviespreview.domain.UseCaseFactory
import com.jpp.moviespreview.ui.model.DomainToUiMapper
import com.jpp.moviespreview.ui.mvp.BasePresenter
import com.jpp.moviespreview.ui.mvp.PresentingView
import com.jpp.moviespreview.ui.model.MoviesConfiguration as uiMoviesConfiguration

/**
 * Contains the Definition of MVP for the Home section
 *
 * Created by jpp on 6/27/17.
 */


/**
 * PresentingView that defines the Home Screen behavior
 */
interface HomeView : PresentingView {

    fun showMoviesConfiguration(moviesConfiguration: uiMoviesConfiguration)

    fun showError()

}


/**
 * Presenter for the Home Screen section.
 */
//TODO test!
class HomePresenter(useCaseFactory: UseCaseFactory) : BasePresenter<HomeView>(useCaseFactory) {


    override fun linkView(viewInstance: HomeView) {
        super.linkView(viewInstance)
        if (mContext.moviesConfiguration == null) {
            executeInBackground({ useCaseFactory.moviesConfiguration().execute(null) }, {
                if (it != null) {
                    mContext.moviesConfiguration = DomainToUiMapper().convertMoviesConfigurationFromDomainModel(it)
                    // at this point, i know mContext.moviesConfiguration will be != null
                    getView()?.showMoviesConfiguration(mContext.moviesConfiguration!!)
                } else {
                    getView()?.showError()
                }
            })
        }
    }
}