package com.jpp.moviespreview.ui.home

import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import com.jpp.moviespreview.R
import com.jpp.moviespreview.extentions.endlessScrolling
import com.jpp.moviespreview.extentions.getUseCaseFactoryAsSystem
import com.jpp.moviespreview.ui.model.Movie
import com.jpp.moviespreview.ui.model.MoviesConfiguration
import com.jpp.moviespreview.ui.mvp.BasePresenterActivity
import kotlinx.android.synthetic.main.home_activity.*
import org.jetbrains.anko.toast

/**
 * Initial screen shown to the user.
 *
 * Created by jpp on 6/28/17.
 */
class HomeActivity : BasePresenterActivity<HomeView, HomePresenter>(), HomeView {

    val adapter by lazy { MoviesAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)
        val layoutManager = LinearLayoutManager(this)
        rv_movies.layoutManager = layoutManager
        rv_movies.addItemDecoration(DividerItemDecoration(this, layoutManager.orientation))
        rv_movies.adapter = adapter
        rv_movies.endlessScrolling({ getPresenter().getNextMoviesPage() })
    }

    // from HomeView

    override fun showMoviesConfiguration(moviesConfiguration: MoviesConfiguration) {
        toast(moviesConfiguration.imagesConfiguration.baseUrl)
    }

    override fun showError() {
        toast("Error baby!!")
    }

    override fun showMoviesPage(page: List<Movie>) {
        adapter.appendMovies(page)
    }
    // From PresentingView

    override fun getView() = this

    override fun createPresenter() = HomePresenter(getUseCaseFactoryAsSystem())

}