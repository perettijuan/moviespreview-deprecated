package com.jpp.moviespreview.ui.home

import android.os.Bundle
import com.jpp.moviespreview.R
import com.jpp.moviespreview.extentions.getUseCaseFactoryAsSystem
import com.jpp.moviespreview.ui.model.MoviesConfiguration
import com.jpp.moviespreview.ui.mvp.BasePresenterActivity
import org.jetbrains.anko.toast

/**
 * Initial screen shown to the user.
 *
 * Created by jpp on 6/28/17.
 */
class HomeActivity : BasePresenterActivity<HomeView, HomePresenter>(), HomeView {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

    }

    // from HomeView

    override fun showMoviesConfiguration(moviesConfiguration: MoviesConfiguration) {
        toast(moviesConfiguration.imagesConfiguration.baseUrl)
    }

    override fun showError() {
        toast("Error baby!!")
    }

    // From PresentingView

    override fun getView() = this

    override fun createPresenter() = HomePresenter(getUseCaseFactoryAsSystem())

}