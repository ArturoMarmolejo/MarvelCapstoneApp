package com.arturomarmolejo.marvelcapstoneapp.utils.typeconverters.characters

import androidx.room.TypeConverter
import com.arturomarmolejo.marvelcapstoneapp.model.character.Url
import com.arturomarmolejo.marvelcapstoneapp.model.comic.Price
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PriceListTypeConverter {

    @TypeConverter
    fun fromPriceList(priceList: List<Price>): String {
        return Gson().toJson(priceList)
    }

    @TypeConverter
    fun toPriceList(priceListString: String): List<Price> {
        val priceListType = object : TypeToken<List<Price>>() {}.type
        return Gson().fromJson(priceListString, priceListType)
    }

}