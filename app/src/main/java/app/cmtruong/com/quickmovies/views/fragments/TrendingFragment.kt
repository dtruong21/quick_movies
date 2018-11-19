package app.cmtruong.com.quickmovies.views.fragments

import android.os.Bundle
import android.support.annotation.WorkerThread
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.adapters.MoviesAdapter
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.models.MoviesResult
import app.cmtruong.com.quickmovies.services.remote.MoviesRemoteAPI
import kotlinx.android.synthetic.main.extras_fragment_container.*
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
        fun getInstance(): TrendingFragment = TrendingFragment()
    }

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("Create view trending")
        val view: View = inflater.inflate(R.layout.fragment_trending, container, false)
        val mRecyclerView = view.findViewById(R.id.rv_movies) as RecyclerView
        mRecyclerView.setHasFixedSize(true)
        mRecyclerView.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        mRecyclerView.adapter = moviesAdapter
        getMovies()
        return view

    }

    fun showMessageError() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.VISIBLE
        rv_movies.visibility = View.GONE
    }

    fun showResults() {
        pb_movie.visibility = View.GONE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.VISIBLE
    }

    fun loadingData() {
        pb_movie.visibility = View.VISIBLE
        movie_error.visibility = View.GONE
        rv_movies.visibility = View.GONE
    }

    @WorkerThread
    private fun getMovies() {
        val apiService = MoviesRemoteAPI.create()
        val call = apiService.getTrendingPerWeek(getString(R.string.api_key))
        loadingData()
        call.enqueue(object : Callback<MoviesResult> {
            override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                showMessageError()
                Timber.d("Request KO")
            }

            override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                val statusCode = response.code()
                if (response.isSuccessful && statusCode == 200) {
                    val results: MoviesResult? = response.body()
                    Timber.d("Results: %s", results.toString())
                    val movies: List<Movies> = results!!.movies!!
                    Timber.d("Movies: %s", movies.toString())
                    moviesAdapter = MoviesAdapter(context, movies)
                    showResults()
                }
            }

        })
    }
}