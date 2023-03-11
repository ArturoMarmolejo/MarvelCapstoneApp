package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.comics

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.TextObject
import com.google.gson.Gson

class TextObjectTypeConverter {
    @TypeConverter
    fun fromTextObject(textObject: TextObject): String {
        return Gson().toJson(textObject)
    }

    @TypeConverter
    fun toTextObject(textObjectString: String): TextObject {
        return Gson().fromJson(textObjectString, TextObject::class.java)
    }
}