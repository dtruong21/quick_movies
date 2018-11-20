package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import kotlinx.android.synthetic.main.extras_fragment_container.*
import timber.log.Timber

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018 November 19th
 */
class FavoriteFragment: Fragment() {

    companion object {
        fun getInstance(): FavoriteFragment = FavoriteFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("Favorite fragment created ...")
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

     private fun showMessageError() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.VISIBLE
        rv_movies.visibility = View.GONE
    }

    private fun showResults(){
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.VISIBLE
    }

    private fun loadingData(){
        pb_movie.visibility = View.VISIBLE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.GONE
    }
}