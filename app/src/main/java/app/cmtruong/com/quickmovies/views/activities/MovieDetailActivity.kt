package app.cmtruong.com.quickmovies.views.activities

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.views.fragments.DetailMovieFragment
import app.cmtruong.com.quickmovies.views.viewmodels.MovieViewModel
import kotlinx.android.synthetic.main.activity_movie_detail.*
import timber.log.Timber

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018, November 26th
 */
class MovieDetailActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        val TAG = MovieDetailActivity::class.java.canonicalName as String

        private const val DEFAULT_POSITION: Int = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        Timber.d("$TAG is created.")

        val position: Int = intent.getIntExtra(getString(R.string.movie_position), DEFAULT_POSITION)
        val movies: ArrayList<Movies> = intent.getParcelableArrayListExtra(getString(R.string.movie_list))

        val mAdapter = MyMovieViewPager(supportFragmentManager)
        mAdapter.setMovies(movies)
        movie_pager.apply {
            adapter = mAdapter
            currentItem = position
        }
        Timber.d("The position $position from the list of %s", movies.toString())
    }

    /**
     * @author cminhtruong
     * @version 1.0
     * @since 2018, December 18th
     */
    class MyMovieViewPager(manager: FragmentManager) : FragmentPagerAdapter(manager) {
        private var moviesList: ArrayList<Movies> = ArrayList()

        override fun getItem(position: Int): Fragment {
            return DetailMovieFragment.getInstance(position, moviesList)
        }

        override fun getCount(): Int = moviesList.size

        /**
         * setup movie list
         */
        fun setMovies(movies: ArrayList<Movies>) = moviesList.addAll(movies)
    }
}
