package app.cmtruong.com.quickmovies.models

import android.os.Parcelable
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