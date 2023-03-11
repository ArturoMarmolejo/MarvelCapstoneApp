package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.creator.Series
import com.google.gson.Gson

class SeriesTypeConverter {
    @TypeConverter
    fun fromSeries(series: Series): String {
        return Gson().toJson(series)
    }

    @TypeConverter
    fun toSeries(seriesString: String): Series {
        return Gson().fromJson(seriesString, Series::class.java)
    }
}