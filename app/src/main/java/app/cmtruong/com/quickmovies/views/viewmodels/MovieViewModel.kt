package app.cmtruong.com.quickmovies.views.viewmodels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.services.dao.AppDatabase
import app.cmtruong.com.quickmovies.services.dao.MovieRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

/**
 * View model class which help to access to Room
 * @author ctruong
 * @version 1.0
 * @since 2019, January 9th
 */
class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: MovieRepository
    private var mMovies: LiveData<List<Movies>>
    private var parentJob = Job()
    private val ioScope = CoroutineScope(parentJob + Dispatchers.Main)

    init {
        val movieDao = AppDatabase.getInstance(application).movieDao()
        mRepository = MovieRepository(movieDao)
        mMovies = mRepository.movies
    }

    /**
     * Add movie to the repository before sending it to the database
     */
    fun insert(movie: Movies) = ioScope.launch(Dispatchers.IO) { mRepository.insert(movie) }

    override fun onCleared() {
        super.onCleared()
        parentJob.cancel()
    }
}