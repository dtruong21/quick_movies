package app.cmtruong.com.quickmovies.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Movies data class
 *
 * @author Davide Truong
 * @version 1.0
 * @since October 19th, 2018
 */
@Entity(tableName = "movies")
@Parcelize
data class Movies constructor(@PrimaryKey var id: Int,
                              var poster_path: String,
                              var overview: String,
                              var release_date: String,
                              var original_title: String,
                              var original_language: String,
                              var title: String,
                              var vote_average: Double,
                              var vote_count: Int,
                              var popularity: Double,
                              var budget: Int,
                              var revenue: Int,
                              var backdrop_path: String) : Parcelable

class MoviesResult constructor(var page: Int,
                               @SerializedName("results") var movies: List<Movies>? = null,
                               var total_page: Int,
                               var total_results: Int)



