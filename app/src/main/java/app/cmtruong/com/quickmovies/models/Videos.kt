package app.cmtruong.com.quickmovies.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Video data class
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 19th, 2018
 */
@Parcelize
data class Videos constructor(var id: String,
                              var key: String,
                              var name: String,
                              var site: String,
                              var size: Int,
                              var type: String) : Parcelable

class VideoResult constructor(var id: Int,
                              @SerializedName("results") var videos: List<Videos>)