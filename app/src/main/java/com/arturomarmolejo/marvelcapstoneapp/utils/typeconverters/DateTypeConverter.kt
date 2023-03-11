package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Comics
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Date
import com.google.gson.Gson

class DateTypeConverter {
    @TypeConverter
    fun fromDate(date: Date): String {
        return Gson().toJson(date)
    }

    @TypeConverter
    fun toDate(dateString: String): Date {
        return Gson().fromJson(dateString, Date::class.java)
    }
}