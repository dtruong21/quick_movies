package app.cmtruong.com.quickmovies.services.dao

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.models.Reviews
import app.cmtruong.com.quickmovies.models.Videos
import timber.log.Timber
import java.util.*

/**
 * My app Room database
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 29th, 2018
 */
@Database(entities = [Movies::class], version = 1)
@TypeConverters(DateTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun movieDao(): MoviesDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null
        @JvmStatic
        val DATABASE_NAME: String = "quick_movie"

        @JvmStatic
        val LOCK: Any = Object()

        /**
         * get new instance of Room Database
         */
        fun getInstance(context: Context): AppDatabase? {
            if (INSTANCE == null) {
                Timber.d("Database instance is not null")
                synchronized(LOCK) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DATABASE_NAME)
                            .fallbackToDestructiveMigration().build()
                }
            }
            Timber.d("Warning! Your database instance is null")
            return INSTANCE
        }

        /**
         * delete the database whenever required
         */
        fun destroyDatabase() {
            INSTANCE = null
        }
    }
}