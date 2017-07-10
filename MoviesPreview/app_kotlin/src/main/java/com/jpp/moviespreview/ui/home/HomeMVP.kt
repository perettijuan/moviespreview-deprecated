package com.jpp.moviespreview.ui.home

import com.jpp.moviespreview.domain.UseCaseFactory
import com.jpp.moviespreview.ui.model.DomainToUiMapper
import com.jpp.moviespreview.ui.model.Movie
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

    fun showMoviesPage(page: List<Movie>)

}


/**
 * Presenter for the Home Screen section.
 */
//TODO test!
class HomePresenter(useCaseFactory: UseCaseFactory) : BasePresenter<HomeView>(useCaseFactory) {

    // since the presenter is never destroyed, we can rely on this
    private var mCurrentPage = 1

    override fun linkView(viewInstance: HomeView) {
        super.linkView(viewInstance)
        if (mContext.moviesConfiguration == null) {
            getMoviesConfiguration()
        } else {
            getNextMoviesPage()
        }
    }


    private fun getMoviesConfiguration() {
        executeInBackground({ useCaseFactory.moviesConfiguration().execute(null) }, {
            if (it != null) {
                mContext.moviesConfiguration = DomainToUiMapper().convertMoviesConfigurationFromDomainModel(it)
                // at this point, i know mContext.moviesConfiguration will be != null
                getView()?.showMoviesConfiguration(mContext.moviesConfiguration!!)
                getNextMoviesPage()
            } else {
                getView()?.showError()
            }
        })
    }

    fun getNextMoviesPage() {
        executeInBackground({ useCaseFactory.topRatedMovies().execute(mCurrentPage) }, {
            if (it != null) {
                // update the page
                mCurrentPage = ++it.page
                val moviesPage = DomainToUiMapper().convertMoviesFromDomainModel(it.movies)
                getView()?.showMoviesPage(moviesPage)
            } else {
                getView()?.showError()
            }
        })
    }
}