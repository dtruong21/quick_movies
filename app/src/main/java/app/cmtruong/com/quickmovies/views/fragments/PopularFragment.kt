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
import kotlinx.android.synthetic.main.fragment_popular.*
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
class PopularFragment : Fragment() {
    companion object {
        @JvmStatic
        private val TAG = PopularFragment::class.java.canonicalName as String

        private val viewModelJob = Job()
        private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

        /**
         * get new Instance of Popular fragment
         */
        fun getInstance(): PopularFragment = PopularFragment()
    }

    private var moviesAdapter: MoviesAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_popular, container, false)
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
            val call = mApi.getPopularMovies(getString(R.string.api_key))
            loadingData()
            call.enqueue(object : Callback<MoviesResult> {
                override fun onResponse(call: Call<MoviesResult>, response: Response<MoviesResult>) {
                    Timber.d("$TAG starts requesting")
                    val statusCode: Int? = response.code()
                    if (response.isSuccessful && statusCode == 200) {
                        Timber.d("$TAG request OK%s", response.body())
                        val result: MoviesResult? = response.body()
                        val movies: List<Movies>? = result?.movies
                        if (movies != null)
                            rv_movies.setupData(movies)
                    }
                }

                override fun onFailure(call: Call<MoviesResult>, t: Throwable) {
                    showMessageError()
                    Timber.d("$TAG request KO, please check your internet")
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