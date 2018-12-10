package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import timber.log.Timber

/**
 * @author cminhtruong
 * @version 1.0
 * @since 2018, December 10th
 */
class DetailMovieFragment : Fragment() {

    companion object {
        @JvmStatic
        val TAG = DetailMovieFragment::class.java.canonicalName as String

        /**
         * get new instance of detail movie fragment
         */
        fun getInstance(): DetailMovieFragment = DetailMovieFragment()
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