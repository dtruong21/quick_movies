package app.cmtruong.com.quickmovies.services.dao

import android.arch.persistence.room.TypeConverter
import java.util.*

/**
 * @author Davide Truong
 * @version 1.0
 * @since October 29th, 2018
 */
class DateTypeConverter {

    @TypeConverter
    fun fromTimestamp(value: Long?): Date? {
        return if (value == null) null else Date(value)
    }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? {
        return date?.time
    }
}