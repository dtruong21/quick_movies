package app.cmtruong.com.quickmovies.views.fragments

import android.content.Intent
import android.os.Bundle
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
import app.cmtruong.com.quickmovies.views.activities.MovieDetailActivity
import kotlinx.android.synthetic.main.fragment_top_rate.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * @author davidetruong
 * @version 1.0
 * @since 2018 November 19th
 */
class TopRatedFragment : Fragment() {
    companion object {

        @JvmStatic
        private val TAG = TopRatedFragment::class.java.canonicalName as String

        private val viewModelJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        /**
         * get new Instance of Top rated Fragment
         */
        fun getInstance(): TopRatedFragment = TopRatedFragment()
    }

    private lateinit var moviesAdapter: MoviesAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_top_rate, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
            val mApi = MoviesRemoteAPI.create()
            val call = mApi.getTopRatedMovies(getString(R.string.api_key))
            loadingData()
            call.enqueue(object : Callback<MoviesResult> {
                override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                    showMessageError()
                }

                override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                    val statusCode: Int? = response.code()
                    if (response.isSuccessful && statusCode == 200) {
                        val results: MoviesResult? = response.body()
                        Timber.d("$TAG results: %s", results.toString())
                        val movies: List<Movies>? = results?.movies
                        Timber.d("Movies: %s", movies.toString())
                        if (movies != null)
                           rv_movies.setupData(movies)
                    }
                }
            })
        }
    }

    private fun RecyclerView.setupData(movies: List<Movies>) {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        this.setHasFixedSize(true)
        this.adapter = MoviesAdapter(movies){
            val intent = Intent(activity, MovieDetailActivity::class.java).apply {
                putExtra(getString(R.string.movie_list), ArrayList(movies))
                putExtra(getString(R.string.movie_position), movies.indexOf(it))
            }
            startActivity(intent)
        }
        showResults()
    }
}