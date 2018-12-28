package app.cmtruong.com.quickmovies.views.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.adapters.ReviewsAdapter
import app.cmtruong.com.quickmovies.adapters.VideosAdapter
import app.cmtruong.com.quickmovies.models.*
import app.cmtruong.com.quickmovies.services.remote.MoviesRemoteAPI
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

/**
 * @author cminhtruong
 * @version 1.0
 * @since 2018, December 10th
 */
class DetailMovieFragment : Fragment() {

    companion object {
        @JvmStatic
        private val TAG = DetailMovieFragment::class.java.canonicalName as String

        private const val MOVIE_POSITION = "MOVIE_POSITION"
        private const val MOVIE_LIST = "MOVIE_LIST"

        private const val POSTER_URL = "http://image.tmdb.org/t/p/w500"

        private val viewModelJob = Job()
        private val defaultScope = CoroutineScope(Dispatchers.Default + viewModelJob)
        /**
         * get new instance of detail movie fragment
         */
        fun getInstance(position: Int, movies: ArrayList<Movies>): DetailMovieFragment {
            val args = Bundle()
            args.putInt(MOVIE_POSITION, position)
            args.putParcelableArrayList(MOVIE_LIST, movies)
            val mFragment = DetailMovieFragment()
            mFragment.arguments = args
            return mFragment
        }

        private var movies: ArrayList<Movies> = ArrayList()
        private var position: Int = 0
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Timber.d("$TAG is created")
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Timber.d("$TAG is started")
        if (arguments != null) {
            position = arguments!!.getInt(MOVIE_POSITION)
            movies = arguments!!.getParcelableArrayList(MOVIE_LIST)
        }
        populateUI(movies[position])
        super.onViewCreated(view, savedInstanceState)
    }

    private fun populateUI(movie: Movies) {
        (activity as AppCompatActivity).setSupportActionBar(toolbar)
        (activity as AppCompatActivity).supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeButtonEnabled(true)
            title = movie.title
            photo.loadImage(POSTER_URL + movie.backdrop_path)
        }

        val language = "Language: ${movie.original_language}"
        val voteCount = "Vote count: ${movie.vote_count}"
        val rate = movie.vote_average / 2
        val release = "Release date: ${movie.release_date}"
        val title = "Title: ${movie.original_title}"
        val popularity = "Popularity: ${movie.popularity}"

        movie_detail_rate_count.text = voteCount
        movie_detail_original_title.text = title
        movie_detail_language.text = language
        movie_detail_overview.text = movie.overview
        movie_detail_release.text = release
        movie_detail_rating_bar.rating = rate.toFloat()
        movie_detail_popularity.text = popularity
        detail_poster.loadImage(POSTER_URL + movie.poster_path)
        getReviews(movie)
        getTrailers(movie)

        add_button.apply {
            setOnClickListener {
                Timber.d("$it is clicked")
            }
        }

        review_button.apply {
            setOnClickListener {
                Timber.d("$it is clicked")
            }
        }

        share_button.apply {
            setOnClickListener {
                val intent = Intent(Intent.ACTION_SEND).apply {
                    type = context.getString(R.string.type_share)
                    putExtra(Intent.EXTRA_SUBJECT, context.getString(R.string.app_name))
                    val textShare = "${movie.title} with rate ${movie.vote_average}. Let's check the information of this film via this link: "
                    putExtra(Intent.EXTRA_TEXT, textShare)
                }
                startActivity(Intent.createChooser(intent, "Share ${movie.original_title}"))
            }
        }
    }

    private fun ImageView.loadImage(url: String) {
        Picasso.get().load(url)
                .fit()
                .centerCrop()
                .error(R.drawable.ic_launcher_background)
                .placeholder(R.drawable.ic_launcher_background)
                .into(this)
    }

    private fun RecyclerView.setupReview(reviews: List<Reviews>) {
        this.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        this.setHasFixedSize(true)
        this.adapter = ReviewsAdapter(reviews)
    }

    private fun RecyclerView.setupTrailer(trailers: List<Videos>) {
        this.layoutManager = LinearLayoutManager(context)
        this.setHasFixedSize(true)
        this.adapter = VideosAdapter(trailers) {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.video_play_url) + it.key)))
        }
    }

    private fun getReviews(movie: Movies) {
        Timber.d("$TAG starts requesting reviews")
        defaultScope.launch {
            MoviesRemoteAPI.create().getMovieReviewsById(movie.id, getString(R.string.api_key)).apply {
                enqueue(object : Callback<ReviewResult> {
                    override fun onFailure(call: Call<ReviewResult>, t: Throwable) {
                        Timber.e("$TAG request reviews KO with error ${t.message}")
                    }

                    override fun onResponse(call: Call<ReviewResult>, response: Response<ReviewResult>) {
                        val statusCode = response.code()
                        if (response.isSuccessful && statusCode == 200) {
                            Timber.d("$TAG request reviews OK")
                            val result: ReviewResult? = response.body()
                            val reviews: List<Reviews>? = result?.reviews
                            if (reviews != null) {
                                movie_reviews_rv.setupReview(reviews)
                            }
                        }
                    }
                })
            }
        }
    }

    private fun getTrailers(movie: Movies) {
        Timber.d("$TAG starts requesting videos")
        defaultScope.launch {
            MoviesRemoteAPI.create().getMovieTrailersById(movie.id, getString(R.string.api_key)).apply {
                enqueue(object : Callback<VideoResult> {
                    override fun onResponse(call: Call<VideoResult>, response: Response<VideoResult>) {
                        val statusCode = response.code()
                        if (response.isSuccessful && statusCode == 200) {
                            Timber.d("$TAG request reviews OK")
                            val result: VideoResult? = response.body()
                            val videos: List<Videos>? = result?.videos
                            if (videos != null) {
                                movie_trailer_rv.setupTrailer(videos)
                            }
                        }
                    }

                    override fun onFailure(call: Call<VideoResult>, t: Throwable) {
                        Timber.e("$TAG request reviews KO with error ${t.message}")
                    }

                })
            }
        }
    }

    override fun onStop() {
        super.onStop()
        viewModelJob.cancel()
    }
}