package app.cmtruong.com.quickmovies.services.remote

import app.cmtruong.com.quickmovies.models.MoviesResult
import app.cmtruong.com.quickmovies.models.Reviews
import app.cmtruong.com.quickmovies.models.Videos
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * @author Davide Truong
 * @since October 26th, 2018
 * @version 1.0
 */
interface MoviesRemoteAPI {

    /**
     * function which retrieves all trending per week
     */
    @GET("trending/movie/week")
    fun getTrendingPerWeek(@Query("api_key") apiKey: String): Call<MoviesResult>

    /**
     * function which retrieves the popular list
     */
    @GET("movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<MoviesResult>

    /**
     * function which retrieves the top rated list
     */
    @GET("movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<MoviesResult>

    /**
     * function which get reviews list by movie id
     */
    @GET("{movie_id}/reviews")
    fun getMovieReviewsById(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<List<Reviews>>

    /**
     * function which get trailer list by movie id
     */
    @GET("{movie_id}/videos")
    fun getMovieTrailersById(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<List<Videos>>

    companion object {
        private const val API_URL: String = "https://api.themoviedb.org/3/"

        /**
         * function which create new instance of retrofit
         */
        fun create(): MoviesRemoteAPI {
            val retrofit = Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                    .build()
            return retrofit.create(MoviesRemoteAPI::class.java)
        }
    }
}