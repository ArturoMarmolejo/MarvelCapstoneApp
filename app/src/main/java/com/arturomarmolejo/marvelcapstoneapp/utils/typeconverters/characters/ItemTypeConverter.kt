package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Item
import com.google.gson.Gson

class ItemTypeConverter {
    @TypeConverter
    fun fromItem(item: Item): String {
        return Gson().toJson(item)
    }

    @TypeConverter
    fun toItem(itemString: String): Item {
        return Gson().fromJson(itemString, Item::class.java)
    }
}