package app.cmtruong.com.quickmovies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Videos
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.videos_items.view.*

/**
 * @author cminhtruong
 * @version 1.0
 * @since 2018, December 17th
 */
class VideosAdapter(private val videos: List<Videos>, private val listener: (Videos) -> Unit) : RecyclerView.Adapter<VideosAdapter.VideosViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder = VideosViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.videos_items, parent, false))

    override fun getItemCount(): Int = videos.size

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        holder.bind(videos[position], listener)
    }

    /**
     * @author cminhtruong
     * @version 1.0
     * @since 2018, December 17th
     */
    class VideosViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        companion object {
            private const val TRAILER_IMAGE_URL = "http://img.youtube.com/vi/"
        }

        private fun ImageView.loadImage(url: String) {
            Picasso.get().load(url)
                    .error(R.drawable.ic_launcher_background)
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(this)
        }

        /**
         * setup video item
         */
        fun bind(video: Videos, listener: (Videos) -> Unit) = with(itemView) {
            itemView.movie_youtube_trailer.loadImage(TRAILER_IMAGE_URL + video.key)
            itemView.movie_trailer_name.text = video.name
            setOnClickListener { listener(video) }
        }
    }
}