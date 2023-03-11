package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Image
import com.google.gson.Gson

class ImageTypeConverter {
    @TypeConverter
    fun fromImage(image: Image): String {
        return Gson().toJson(image)
    }

    @TypeConverter
    fun toImage(imageString: String): Image {
        return Gson().fromJson(imageString, Image::class.java)
    }

}