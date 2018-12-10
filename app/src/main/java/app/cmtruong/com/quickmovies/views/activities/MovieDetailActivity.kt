package app.cmtruong.com.quickmovies.views.activities

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Movies
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
        Timber.d("The position $position from the list of %s", movies.toString())
    }
}
