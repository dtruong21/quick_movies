package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.annotation.UiThread
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Genres
import app.cmtruong.com.quickmovies.models.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.*
import timber.log.Timber

/**
 * @author cminhtruong
 * @version 1.0
 * @since 2018, December 10th
 */
class DetailMovieFragment : Fragment() {

    companion object {
        @JvmStatic
        private val TAG = DetailMovieFragment::class.java.canonicalName as String

        private const val MOVIE_POSITION = "MOVIE_POSITION"
        private const val MOVIE_LIST = "MOVIE_LIST"

        private const val POSTER_URL = "http://image.tmdb.org/t/p/w185"
        /**
         * get new instance of detail movie fragment
         */
        fun getInstance(position: Int, movies: ArrayList<Movies>): DetailMovieFragment {
            val args = Bundle()
            args.putInt(MOVIE_POSITION, position)
            args.putParcelableArrayList(MOVIE_LIST, movies)
            val mFragment = DetailMovieFragment()
            mFragment.arguments = args
            return mFragment
        }

        private var movies: ArrayList<Movies> = ArrayList()
        private var position: Int = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("$TAG is started")
        if (arguments != null) {
            position = arguments!!.getInt(MOVIE_POSITION)
        }

        runBlocking {
            launch(coroutineContext) { populateUI(movies[position]) }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populateUI(movie: Movies) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setHomeButtonEnabled(true)
            title = movie.title
            photo.loadImage(POSTER_URL + movie.backdrop_path)
        }
        val genres: List<Genres> = movie.genres
        val sb = StringBuilder()
        for (genre in genres) {
            sb.append("${genre.name}, ")
        }
        movie_detail_genres.text = sb.toString()
        movie_detail_language.text = movie.original_language
        movie_detail_budget.text = movie.budget.toString()
        movie_detail_rate.text = movie.vote_average.toString()
        movie_detail_overview.text = movie.overview
        detail_poster.loadImage(POSTER_URL + movie.poster_path)
    }

    private fun ImageView.loadImage(url: String) {
        Picasso.get().load(url)
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(this)
    }

}