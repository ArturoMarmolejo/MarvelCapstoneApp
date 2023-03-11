package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Thumbnail
import com.google.gson.Gson

class ThumbnailTypeConverter {
    @TypeConverter
    fun fromThumbnail(thumbnail: Thumbnail): String {
        return Gson().toJson(thumbnail)
    }

    @TypeConverter
    fun toThumbnail(thumbnailString: String): Thumbnail {
        return Gson().fromJson(thumbnailString, Thumbnail::class.java)
    }
}