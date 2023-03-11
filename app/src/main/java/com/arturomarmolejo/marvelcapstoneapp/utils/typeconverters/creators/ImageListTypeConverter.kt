package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Image
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ImageListTypeConverter {
    @TypeConverter
    fun fromImageList(imageList: List<Image>): String {
        return Gson().toJson(imageList)
    }

    @TypeConverter
    fun toImageList(imageListString: String): List<Image> {
        val imageListType = object : TypeToken<List<Image>>() {}.type
        return Gson().fromJson(imageListString, imageListType)
    }
}