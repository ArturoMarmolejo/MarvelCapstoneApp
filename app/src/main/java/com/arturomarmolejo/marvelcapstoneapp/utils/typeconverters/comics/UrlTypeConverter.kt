package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Url
import com.google.gson.Gson

class UrlTypeConverter {
    @TypeConverter
    fun fromUrl(url: Url): String {
        return Gson().toJson(url)
    }

    @TypeConverter
    fun toUrl(urlString: String): Url {
        return Gson().fromJson(urlString, Url::class.java)
    }
}