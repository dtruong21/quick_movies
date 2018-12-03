package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.adapters.MoviesAdapter
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.models.MoviesResult
import app.cmtruong.com.quickmovies.services.remote.MoviesRemoteAPI
import kotlinx.android.synthetic.main.fragment_trending.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 25th, 2018
 */
class TrendingFragment : Fragment() {
    companion object {
        @JvmStatic
        val TAG = TrendingFragment::class.java.canonicalName as String

        private val viewModelJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        fun getInstance(): TrendingFragment = TrendingFragment()
    }

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_trending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("$TAG view is started")
        getMovies()
    }

    private fun showMessageError() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.VISIBLE
        rv_movies.visibility = View.GONE
    }

    private fun showResults() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.VISIBLE
    }

    private fun loadingData() {
        pb_movie.visibility = View.VISIBLE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.GONE
    }

    private fun getMovies() {
        Timber.d("$TAG starts service")
        uiScope.launch {
            val apiService = MoviesRemoteAPI.create()
            val call = apiService.getTrendingPerWeek(getString(R.string.api_key))
            Timber.d("$TAG start request API")
            loadingData()
            call.enqueue(object : Callback<MoviesResult> {
                override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                    showMessageError()
                    Timber.d("$TAG Request KO")
                }

                override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                    val statusCode = response.code()
                    Timber.d("$TAG success $statusCode")
                    if (response.isSuccessful && statusCode == 200) {
                        val results: MoviesResult? = response.body()
                        Timber.d("Results: %s", results.toString())
                        val movies: List<Movies>? = results?.movies
                        if (movies != null)
                            moviesAdapter = MoviesAdapter(context, movies)
                        Timber.d("Movies: %s", movies.toString())
                        rv_movies.setHasFixedSize(true)
                        rv_movies.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                        rv_movies.adapter = moviesAdapter
                        showResults()
                    }
                }
            })
        }
    }
}