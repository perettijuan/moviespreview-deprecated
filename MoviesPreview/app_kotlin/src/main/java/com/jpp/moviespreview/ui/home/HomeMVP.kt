package com.jpp.moviespreview.ui.home

import com.jpp.moviespreview.domain.interactors.UseCase
import com.jpp.moviespreview.domain.model.MoviesConfiguration
import com.jpp.moviespreview.ui.model.DomainToUiMapper
import com.jpp.moviespreview.ui.mvp.BasePresenter
import com.jpp.moviespreview.ui.mvp.PresentingView
import javax.inject.Inject
import com.jpp.moviespreview.ui.model.MoviesConfiguration as uiMoviesConfiguration

/**
 * Created by jpp on 6/27/17.
 */

interface HomeView : PresentingView {

    fun showMoviesConfiguration(moviesConfiguration: uiMoviesConfiguration)

    fun showError()

}


class HomePresenter : BasePresenter<HomeView>() {


    @Inject
    lateinit var mUseCase: UseCase<Any, MoviesConfiguration>


    override fun linkView(viewInstance: HomeView) {
        super.linkView(viewInstance)
        if (mContext.moviesConfiguration == null) {
            executeInBackground({ mUseCase.execute(null) }, {
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