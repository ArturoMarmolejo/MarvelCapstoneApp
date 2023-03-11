package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators


import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Creators
import com.google.gson.Gson

class CreatorTypeConverter {
    @TypeConverter
    fun fromCreator(creator: Creators): String {
        return Gson().toJson(creator)
    }

    @TypeConverter
    fun toCreator(creatorString: String): Creators {
        return Gson().fromJson(creatorString, Creators::class.java)
    }
}