package app.cmtruong.com.quickmovies.services.dao

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread
import app.cmtruong.com.quickmovies.models.Movies

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 29th, 2018
 */
class MovieRepository(private val moviesDao: MoviesDao) {

    var movies: LiveData<List<Movies>> = moviesDao.loadMovies()

    @WorkerThread
    suspend fun insert(movie: Movies) {
        moviesDao.addFavoriteMovie(movie)
    }
}