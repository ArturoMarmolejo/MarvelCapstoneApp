package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Characters
import com.arturomarmolejo.marvelcapstoneapp.model.comic.CollectedIssue
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