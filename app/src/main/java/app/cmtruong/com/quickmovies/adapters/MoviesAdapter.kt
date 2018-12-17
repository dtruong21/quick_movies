package app.cmtruong.com.quickmovies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Movies
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movies_item.view.*
import timber.log.Timber

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 25th, 2018
 */
class MoviesAdapter(private val movies: List<Movies>, private val listener: (Movies) -> Unit) : RecyclerView.Adapter<MoviesAdapter.MoviesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view: View = inflater.inflate(R.layout.movies_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = movies.size


    /**
     * Get movies from adapter
     */
    fun getMovies(): List<Movies> = movies

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie: Movies = movies[position]
        holder.bind(movie, listener)
    }

    /**
     * Movie ViewHolder class
     * @author Davide Truong
     * @since October 26th, 2018
     * @version 1.0
     */
    class MoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val posterMovie: ImageView = view.poster_movie

        companion object {
            @JvmStatic
            private val POSTER_URL = "http://image.tmdb.org/t/p/w185"
        }

        private fun ImageView.loadImage(url: String) {
            Picasso.get().load(url)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(this)
        }

        /**
         * bind the view
         */
        fun bind(movie: Movies, listener: (Movies) -> Unit) = with(itemView) {
            posterMovie.loadImage(POSTER_URL + movie.poster_path)
            setOnClickListener { listener(movie) }
        }
    }
}

