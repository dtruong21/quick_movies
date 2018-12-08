package app.cmtruong.com.quickmovies.views.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import app.cmtruong.com.quickmovies.R
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
        Timber.tag(TAG)
        Timber.d("$TAG is created.")
    }
}
