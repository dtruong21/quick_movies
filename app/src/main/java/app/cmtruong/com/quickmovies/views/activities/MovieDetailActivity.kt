package app.cmtruong.com.quickmovies.views.activities

import android.content.Intent
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
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)
        Timber.d("$TAG is created.")

        val intent: Intent? = this.intent
        val bundle: Bundle? = intent!!.extras

        val position: Int? = bundle?.getInt(getString(R.string.movie_position))
        val movies: ArrayList<Movies>? = bundle?.getParcelableArrayList<Movies>(getString(R.string.movie_list))
        Timber.d("The position $position from the list of ${movies.toString()}")
    }
}
