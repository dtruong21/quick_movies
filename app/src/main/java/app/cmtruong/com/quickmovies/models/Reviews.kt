package app.cmtruong.com.quickmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Review data class
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 19th, 2018
 */
@Parcelize
data class Reviews constructor(var id: String,
                               var author: String,
                               var content: String,
                               var url: String) : Parcelable

class ReviewResult constructor(var id: Int,
                               var page: Int,
                               @SerializedName("results") var reviews: List<Reviews>? = null,
                               var total_pages: Int,
                               var total_results: Int)