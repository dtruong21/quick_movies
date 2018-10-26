package app.cmtruong.com.quickmovies.views.fragments

import android.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import kotlinx.android.synthetic.main.extras_fragment_container.*

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 25th, 2018
 */
class TrendingFragment : Fragment() {
    companion object {
        @JvmStatic
        val TAG = TrendingFragment::class.java.canonicalName as String

        @JvmStatic
        fun getInstance(): TrendingFragment = TrendingFragment()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.fragment_trending, container, false)
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