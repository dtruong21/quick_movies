package app.cmtruong.com.quickmovies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Videos

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

        /**
         * setup video item
         */
        fun bind(video: Videos, listener: (Videos) -> Unit) = with(itemView) {
            setOnClickListener { listener(video) }
        }
    }
}