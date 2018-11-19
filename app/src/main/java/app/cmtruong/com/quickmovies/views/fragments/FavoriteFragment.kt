package app.cmtruong.com.quickmovies.views.fragments

import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.extras_fragment_container.*

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018 November 19th
 */
class FavoriteFragment: Fragment() {

    companion object {
        fun getInstance(): FavoriteFragment = FavoriteFragment()
    }

    fun showMessageError() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.VISIBLE
        rv_movies.visibility = View.GONE
    }

    fun showResults(){
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.VISIBLE
    }

    fun loadingData(){
        pb_movie.visibility = View.VISIBLE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.GONE
    }
}