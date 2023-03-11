package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Date
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DateListTypeConverter {
    @TypeConverter
    fun fromDateList(dateList: List<Date>): String {
        return Gson().toJson(dateList)
    }

    @TypeConverter
    fun toDateList(dateListString: String): List<Date> {
        val dateListType = object : TypeToken<List<Date>>() {}.type
        return Gson().fromJson(dateListString, dateListType)
    }
}