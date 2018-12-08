package app.cmtruong.com.quickmovies.services.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import app.cmtruong.com.quickmovies.models.Movies

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 29th, 2018
 */
@Dao
interface MoviesDao {

    /**
     * get all data from the database
     */
    @Query("SELECT * FROM movies")
    fun loadMovies(): LiveData<List<Movies>>

    /**
     * insert new value in the table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addFavoriteMovie(movie: Movies)

    /**
     * update
     */
    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMovie(movie: Movies)

    /**
     * delete
     */
    @Delete
    fun deleteMovie(movie: Movies)

    /**
     * get movie by id
     */
    @Query("SELECT * FROM movies WHERE id = :id")
    fun getMovie(id: Int): LiveData<Movies>
}