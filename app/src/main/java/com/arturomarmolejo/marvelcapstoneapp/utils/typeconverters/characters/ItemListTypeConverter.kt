package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Item
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ItemListTypeConverter {

    @TypeConverter
    fun fromItemList(itemList: List<Item>): String {
        return Gson().toJson(itemList)
    }

    @TypeConverter
    fun toItemList(itemListString: String): List<Item> {
        val itemListType = object : TypeToken<List<Item>>() {}.type
        return Gson().fromJson(itemListString, itemListType)
    }
}