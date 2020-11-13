package tech.ru1t3rl.madlevel5task2

import android.util.Log
import androidx.room.TypeConverter
import java.time.LocalDate
import java.time.format.FormatStyle

class Converters {

    @TypeConverter
    fun dateToLong(date: LocalDate?): String? {
        return date.toString()
    }

    @TypeConverter
    fun stringToDate(date: String?): LocalDate? {
        val dateArray = date?.split('-')
        return LocalDate.of(dateArray?.get(0)!!.toInt(), dateArray[1].toInt(), dateArray[2].toInt())
    }

}