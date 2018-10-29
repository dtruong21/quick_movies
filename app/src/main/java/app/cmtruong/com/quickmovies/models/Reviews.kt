package app.cmtruong.com.quickmovies.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
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