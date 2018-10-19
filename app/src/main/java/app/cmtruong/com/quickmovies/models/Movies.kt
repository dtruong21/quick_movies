package app.cmtruong.com.quickmovies.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Movies data class
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 19th, 2018
 */
@Parcelize
data class Movies(var id: Int, var poster_path: String,
                  var overview: String, var release_date: String, var original_title: String,
                  var original_language: String, var title: String, var vote_average: Double, var backdrop_path: String) : Parcelable

