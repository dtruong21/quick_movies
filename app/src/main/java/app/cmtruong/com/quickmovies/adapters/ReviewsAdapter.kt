package app.cmtruong.com.quickmovies.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.cmtruong.com.quickmovies.R
import app.cmtruong.com.quickmovies.models.Reviews

/**
 * @author cminhtruong
 * @version 1.0
 * @since 2018, December 17th
 */
class ReviewsAdapter(private val reviews: List<Reviews>, private val listener: (Reviews) -> Unit) : RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewsViewHolder = ReviewsViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.reviews_item, parent, false))

    override fun getItemCount(): Int = reviews.size

    override fun onBindViewHolder(holder: ReviewsViewHolder, position: Int) {
        holder.bind(reviews[position], listener)
    }


    /**
     * @author cminhtruong
     * @version 1.0
     * @since 2018, December 17th
     */
    class ReviewsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        /**
         * setup review item
         */
        fun bind(review: Reviews, listener: (Reviews) -> Unit) = with(itemView) {
            // TODO
            setOnClickListener { listener(review) }
        }
    }
}