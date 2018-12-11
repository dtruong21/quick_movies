package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Movies
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
        /**
         * get new instance of detail movie fragment
         */
        fun getInstance(position: Int, movies: ArrayList<Movies>): DetailMovieFragment{
            val args = Bundle()
            args.putInt(MOVIE_POSITION, position)
            args.putParcelableArrayList(MOVIE_LIST, movies)
            val mFragment = DetailMovieFragment()
            mFragment.arguments = args
            return mFragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("$TAG is started")
        super.onViewCreated(view, savedInstanceState)
    }

}