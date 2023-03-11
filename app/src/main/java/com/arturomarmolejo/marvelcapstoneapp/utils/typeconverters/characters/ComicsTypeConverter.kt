package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Comics
import com.google.gson.Gson

class ComicsTypeConverter {

    @TypeConverter
    fun fromComics(comics: Comics): String {
        return Gson().toJson(comics)
    }

    @TypeConverter
    fun toComics(comicsString: String): Comics {
        return Gson().fromJson(comicsString, Comics::class.java)
    }


}