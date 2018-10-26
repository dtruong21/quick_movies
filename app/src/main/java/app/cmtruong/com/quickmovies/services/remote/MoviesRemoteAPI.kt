package app.cmtruong.com.quickmovies.services.remote

import android.content.res.Resources
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Movies
import app.cmtruong.com.quickmovies.models.Reviews
import app.cmtruong.com.quickmovies.models.Videos
import okhttp3.Interceptor
import okhttp3.OkHttpClient
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

    @GET("/trending/movie/week")
    fun getTrendingPerWeek(@Query("api_key") apiKey: String): Call<List<Movies>>

    @GET("/movie/popular")
    fun getPopularMovies(@Query("api_key") apiKey: String): Call<List<Movies>>

    @GET("/movie/top_rated")
    fun getTopRatedMovies(@Query("api_key") apiKey: String): Call<List<Movies>>

    @GET("{movie_id}/reviews")
    fun getMovieReviewsById(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<List<Reviews>>

    @GET("{movie_id}/videos")
    fun getMovieTrailersById(@Path("movie_id") id: Int, @Query("api_key") apiKey: String): Call<List<Videos>>

    companion object {
        fun create(): MoviesRemoteAPI {
            val retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
                    .baseUrl(Resources.getSystem().getString(R.string.api_url)).build()
            return retrofit.create(MoviesRemoteAPI::class.java)
        }
    }
}