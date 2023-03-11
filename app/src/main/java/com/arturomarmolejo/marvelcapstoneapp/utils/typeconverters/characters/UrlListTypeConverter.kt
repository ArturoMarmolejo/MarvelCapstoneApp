package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class UrlListTypeConverter {
    @TypeConverter
    fun fromUrlList(urlList: List<Url>): String {
        return Gson().toJson(urlList)
    }

    @TypeConverter
    fun toUrlList(urlListString: String): List<Url> {
        val urlListType = object : TypeToken<List<Url>>() {}.type
        return Gson().fromJson(urlListString, urlListType)
    }
}