package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Characters
import com.google.gson.Gson

class CharactersTypeConverter {

    @TypeConverter
    fun fromCharacter(character: Characters): String {
        return Gson().toJson(character)
    }

    @TypeConverter
    fun toCharacter(characterString: String): Characters {
        return Gson().fromJson(characterString, Characters::class.java)
    }

}