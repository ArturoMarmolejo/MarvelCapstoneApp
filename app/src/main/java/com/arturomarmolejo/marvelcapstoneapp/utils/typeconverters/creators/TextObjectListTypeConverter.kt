package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.creators

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.TextObject
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class TextObjectListTypeConverter {
    @TypeConverter
    fun fromTextObjectList(textObjectList: List<TextObject>): String {
        return Gson().toJson(textObjectList)
    }

    @TypeConverter
    fun toTextObjectList(textObjectListString: String): List<TextObject> {
        val textObjectListType = object : TypeToken<List<TextObject>>() {}.type
        return Gson().fromJson(textObjectListString, textObjectListType)
    }
}