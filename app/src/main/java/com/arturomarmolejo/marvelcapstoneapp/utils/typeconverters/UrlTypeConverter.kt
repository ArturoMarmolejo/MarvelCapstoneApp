package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Events
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
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